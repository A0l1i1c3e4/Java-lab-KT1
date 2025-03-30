package com.example.hits_java_kt1.Controllers;

import com.example.hits_java_kt1.Entities.Schedule;
import com.example.hits_java_kt1.Entities.PeriodSchedule;
import com.example.hits_java_kt1.Entities.SlotSchedule;
import com.example.hits_java_kt1.Repositories.PeriodScheduleRepository;
import com.example.hits_java_kt1.Repositories.ScheduleRepository;
import com.example.hits_java_kt1.Repositories.SlotScheduleRepository;
import com.example.hits_java_kt1.blanks.ScheduleBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PeriodScheduleRepository periodScheduleRepository;

    @Autowired
    private SlotScheduleRepository slotScheduleRepository;

    /*@GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable String id) {
        return scheduleRepository.findById(id)
                .map(schedule -> new ResponseEntity<>(schedule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*
    @GetMapping()
    public ResponseEntity<ScheduleWithPeriods> getFullSchedule(@RequestParam String id, @RequestParam String name) {

        Optional<Schedule> scheduleOpt = (id != null)
                ? scheduleRepository.findById(id)
                : scheduleRepository.findByScheduleName(name);



        if (scheduleOpt.isPresent()) {
            Schedule schedule = scheduleOpt.get();
            List<PeriodSchedule> periods = periodScheduleRepository.findByScheduleIdOrderBySlotBeginTimeAsc(schedule.getId());

            List<PeriodSchedule> sortedPeriods = periods.stream().sorted(Comparator.comparing(period-> {
                SlotSchedule slot = slotScheduleRepository.findById(period.getSlotId()).orElse(null);
                return (slot == null) ? slot.getBeginTime() : null; }).toList

            })
            // Сортируем периоды по времени начала слота
            //Collections.sort(periods, Comparator.comparing(PeriodSchedule::getSlotBeginTime));

            return ResponseEntity.ok(sortedPeriods);
        }
    }
    */
    // Вспомогательный класс для объединения расписания и связанных периодов
    static class ScheduleWithPeriods {
        private final Schedule schedule;
        private final List<PeriodSchedule> periods;

        public ScheduleWithPeriods(Schedule schedule, List<PeriodSchedule> periods) {
            this.schedule = schedule;
            this.periods = periods;
        }

        public Schedule getSchedule() {
            return schedule;
        }

        public List<PeriodSchedule> getPeriods() {
            return periods;
        }
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleBlank schedule) {
        Schedule schedule1 = new Schedule();
        schedule1.setScheduleName(schedule.getScheduleName());
        scheduleRepository.save(schedule1);
        return ResponseEntity.status(200).body("Расписание успешно создан.");
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable String id, @RequestBody Schedule scheduleDetails) {
        if (!scheduleRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Schedule existingSchedule = scheduleRepository.getOne(id);
        existingSchedule.setScheduleName(scheduleDetails.getScheduleName());
        existingSchedule.setUpdateDate(Instant.now()); // Обновляем дату обновления

        Schedule updatedSchedule = scheduleRepository.save(existingSchedule);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable String id) {
        if (!scheduleRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Расписание не найден.");
        }

        scheduleRepository.deleteById(id);
        return ResponseEntity.status(200).body("Расписание успешно удалён.");
    }
}