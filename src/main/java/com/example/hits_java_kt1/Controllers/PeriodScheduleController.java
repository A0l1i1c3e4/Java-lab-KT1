package com.example.hits_java_kt1.Controllers;

import com.example.hits_java_kt1.Entities.PeriodSchedule;
import com.example.hits_java_kt1.Entities.SlotSchedule;
import com.example.hits_java_kt1.Repositories.PeriodScheduleRepository;
import com.example.hits_java_kt1.Repositories.SlotScheduleRepository;
import com.example.hits_java_kt1.Repositories.ScheduleRepository;
import com.example.hits_java_kt1.Repositories.EmployeeRepository;
import com.example.hits_java_kt1.blanks.PeriodScheduleBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/period-schedules")
public class PeriodScheduleController {

    @Autowired
    private PeriodScheduleRepository periodScheduleRepository;
    @Autowired
    private SlotScheduleRepository slotScheduleRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping
    public List<PeriodSchedule> getAllPeriodSchedules() {
        return periodScheduleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodSchedule> getPeriodSchedulesById(@PathVariable String id) {
        return periodScheduleRepository.findById(id)
                .map(periodSchedule -> new ResponseEntity<>(periodSchedule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createPeriodSchedule( @RequestBody PeriodScheduleBlank periodSchedule) {
        try {
            List<SlotSchedule> overlappingSlots = slotScheduleRepository.findOverlappingSlots(

                slotScheduleRepository.findById(periodSchedule.getSlotId()).get().getBeginTime(),
                slotScheduleRepository.findById(periodSchedule.getSlotId()).get().getEndTime()
            );

            if (!overlappingSlots.isEmpty()) {
                return ResponseEntity.status(409).body("Перекрытие временных интервалов с существующими слотами.");
            }

            if (periodSchedule.getSlotId() == null || periodSchedule.getSlotId().trim().isEmpty()) {
                return ResponseEntity.status(400).body("Идентификатор слота, реализуемого сущностью периода не должно быть пустым.");
            }
            if (periodSchedule.getScheduleId() == null) {
                return ResponseEntity.status(400).body("Идентификатор сущности расписания, в рамках которой существует слот не должен быть пустым.");
            }
            if (periodSchedule.getAdministratorId() == null) {
                return ResponseEntity.status(400).body("Идентификатор владельца слота не должна быть пустой.");
            }
            if (!slotScheduleRepository.existsById(periodSchedule.getSlotId())) {
                return ResponseEntity.status(400).body("Неверный идентификатор слота.");
            }
            if (!scheduleRepository.existsById(periodSchedule.getScheduleId())) {
                return ResponseEntity.status(400).body("Неверный идентификатор сущности расписания.");
            }
            if (!employeeRepository.existsById(periodSchedule.getAdministratorId())) {
                return ResponseEntity.status(400).body("Неверный идентификатор администратора.");
            }
            if (!employeeRepository.existsById(periodSchedule.getExecutorId()) && periodSchedule.getExecutorId() != null) {
                return ResponseEntity.status(404).body("Неверный идентификатор  исполнителя слота.");
            }


            PeriodSchedule periodSchedule1 = new PeriodSchedule();
            periodSchedule1.setSlotId(periodSchedule.getSlotId());
            periodSchedule1.setScheduleId(periodSchedule.getScheduleId());
            periodSchedule1.setSlotType(periodSchedule.getSlotType());
            periodSchedule1.setAdministratorId(periodSchedule.getAdministratorId());
            if (periodSchedule.getExecutorId() != periodSchedule.getAdministratorId()) {
                periodSchedule1.setExecutorId(periodSchedule.getExecutorId());
            }
            periodScheduleRepository.save(periodSchedule1);

            return ResponseEntity.status(200).body("Период расписания успешно создан.");
        }
        catch (Exception error){
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }
    /*
    @PutMapping("/{id}")
    public ResponseEntity<PeriodSchedule> updatePeriodSchedule(
            @PathVariable String id,
            @RequestBody PeriodSchedule periodScheduleDetails
    ) {
        if (!periodScheduleRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PeriodSchedule existingPeriodSchedule = periodScheduleRepository.getOne(id);
        existingPeriodSchedule.setSlotId(periodScheduleDetails.getSlotId());
        existingPeriodSchedule.setScheduleId(periodScheduleDetails.getScheduleId());
        existingPeriodSchedule.setSlotType(periodScheduleDetails.getSlotType());
        existingPeriodSchedule.setAdministratorId(periodScheduleDetails.getAdministratorId());
        existingPeriodSchedule.setExecutorId(periodScheduleDetails.getExecutorId());

        PeriodSchedule updatedPeriodSchedule = periodScheduleRepository.save(existingPeriodSchedule);
        return new ResponseEntity<>(updatedPeriodSchedule, HttpStatus.OK);
    }
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeriodSchedule(@PathVariable String id) {
        if (!periodScheduleRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Период расписания не найден.");
        }

        periodScheduleRepository.deleteById(id);
        return ResponseEntity.status(200).body("Период расписания успешно удалён.");
    }
}