package com.example.hits_java_kt1.Controllers;

import com.example.hits_java_kt1.Entities.TemplateSchedule;
import com.example.hits_java_kt1.Repositories.TemplateScheduleRepository;
import com.example.hits_java_kt1.blanks.TemplateScheduleBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template-schedules")
public class TemplateScheduleController {

    @Autowired
    private TemplateScheduleRepository templateScheduleRepository;

    @GetMapping
    public List<TemplateSchedule> getAllTemplateSchedules() {
        return templateScheduleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplateScheduleById(@PathVariable String id) {
        return templateScheduleRepository.findById(id)
                .map(templateSchedule -> new ResponseEntity<>(templateSchedule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<?> createTemplateSchedule(@RequestParam String templateSchedule) {
        try {
            if (templateSchedule == null) {
                return ResponseEntity.status(400).body("Тип шаблона не должно быть пустым.");
            }
            TemplateSchedule templateSchedule1 = new TemplateSchedule();
            templateSchedule1.setTemplateType(templateSchedule);
            templateScheduleRepository.save(templateSchedule1);
            return ResponseEntity.status(200).body("Шаблон расписания успешно создан.");
        }
        catch (Exception error){
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }
    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTemplateSchedule(
            @PathVariable String id,
            @RequestBody TemplateSchedule templateScheduleDetails
    ) {
        if (!templateScheduleRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TemplateSchedule existingTemplateSchedule = templateScheduleRepository.getOne(id);
        existingTemplateSchedule.setTemplateType(templateScheduleDetails.getTemplateType());

        TemplateSchedule updatedTemplateSchedule = templateScheduleRepository.save(existingTemplateSchedule);
        return new ResponseEntity<>(updatedTemplateSchedule, HttpStatus.OK);
    }
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTemplateSchedule(@PathVariable String id) {
        if (!templateScheduleRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Шаблон расписания не найден.");
        }

        templateScheduleRepository.deleteById(id);
        return ResponseEntity.status(200).body("Шаблон расписания успешно удалён.");
    }
}