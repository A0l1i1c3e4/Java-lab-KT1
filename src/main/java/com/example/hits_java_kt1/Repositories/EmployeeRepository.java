package com.example.hits_java_kt1.Repositories;

import com.example.hits_java_kt1.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, String> {
}