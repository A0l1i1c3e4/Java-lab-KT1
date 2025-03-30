package com.example.hits_java_kt1.Controllers;

import com.example.hits_java_kt1.Entities.Employee;
import com.example.hits_java_kt1.Repositories.EmployeeRepository;
import com.example.hits_java_kt1.blanks.EmployeeBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<?> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        return employeeRepository.findById(id)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeBlank employee) {
        if (employee.getEmployeeName() == null || employee.getEmployeeName().trim().isEmpty()) {
            return ResponseEntity.status(400).body("Имя сотрудника не должно быть пустым.");
        }
        if (employee.getStatus() == null) {
            return ResponseEntity.status(400).body("Статус сотрудника не должен быть пустым.");
        }
        if (employee.getPosition() == null) {
            return ResponseEntity.status(400).body("Позиция сотрудника не должна быть пустой.");
        }
        Employee employee1 = new Employee();
        employee1.setEmployeeName(employee.getEmployeeName());
        employee1.setStatus(employee.getStatus());
        employee1.setPosition(employee.getPosition());
        employeeRepository.save(employee1);

        return ResponseEntity.status(200).body("Сотрудник успешно создан.");
    }
    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody Employee employeeDetails) {
        if (!employeeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Employee updatedEmployee = employeeRepository.getOne(id);
        updatedEmployee.setEmployeeName(employeeDetails.getEmployeeName());
        updatedEmployee.setPosition(employeeDetails.getPosition());
        updatedEmployee.setStatus(employeeDetails.getStatus());

        Employee savedEmployee = employeeRepository.save(updatedEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Сотрудник не найден.");
        }

        employeeRepository.deleteById(id);
        return ResponseEntity.status(200).body("Сотрудник успешно удалён.");
    }
}