package com.example.hits_java_kt1.Controllers;

import com.example.hits_java_kt1.Entities.SlotSchedule;
import com.example.hits_java_kt1.Repositories.SlotScheduleRepository;
import com.example.hits_java_kt1.Repositories.TemplateScheduleRepository;
import com.example.hits_java_kt1.blanks.SlotScheduleBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slot-schedules")
public class SlotScheduleController {

    @Autowired
    private SlotScheduleRepository slotScheduleRepository;
    @Autowired
    private TemplateScheduleRepository templateScheduleRepository;

    @GetMapping
    public List<?> getAllSlotSchedules() {
        return slotScheduleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSlotScheduleById(@PathVariable String id) {
        return slotScheduleRepository.findById(id)
                .map(slotSchedule -> new ResponseEntity<>(slotSchedule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createSlotSchedule(@RequestBody SlotScheduleBlank slotSchedule) {
        if (slotSchedule.getScheduleTemplateId() == null) {
            return ResponseEntity.status(400).body("Идентификатор шаблона расписания не должен быть пустым.");
        }
        if (slotSchedule.getBeginTime() == null) {
            return ResponseEntity.status(400).body("Время начала слота не должен быть пустым.");
        }
        if (slotSchedule.getEndTime() == null) {
            return ResponseEntity.status(400).body("Время завершения слота не должен быть пустым.");
        }
        if (!templateScheduleRepository.existsById(slotSchedule.getScheduleTemplateId())) {
            return ResponseEntity.status(400).body("Неверный идентификатор шаблона расписания.");
        }
        SlotSchedule slotSchedule1 = new SlotSchedule();
        slotSchedule1.setScheduleTemplateId(slotSchedule.getScheduleTemplateId());
        slotSchedule1.setBeginTime(slotSchedule.getBeginTime());
        slotSchedule1.setEndTime(slotSchedule.getEndTime());
        slotScheduleRepository.save(slotSchedule1);
        return ResponseEntity.status(200).body("Слот расписания успешно создан.");
    }
    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSlotSchedule(
            @PathVariable String id,
            @RequestBody SlotSchedule slotScheduleDetails
    ) {
        if (!slotScheduleRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SlotSchedule existingSlotSchedule = slotScheduleRepository.getOne(id);
        existingSlotSchedule.setBeginTime(slotScheduleDetails.getBeginTime());
        existingSlotSchedule.setEndTime(slotScheduleDetails.getEndTime());

        SlotSchedule updatedSlotSchedule = slotScheduleRepository.save(existingSlotSchedule);
        return new ResponseEntity<>(updatedSlotSchedule, HttpStatus.OK);
    }
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlotSchedule(@PathVariable String id) {
        if (!slotScheduleRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Слот расписания не найден.");
        }

        slotScheduleRepository.deleteById(id);
        return ResponseEntity.status(200).body("Слот расписания успешно удалён.");
    }
}