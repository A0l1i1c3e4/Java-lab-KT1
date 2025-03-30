package com.example.hits_java_kt1.Repositories;

import com.example.hits_java_kt1.Entities.TemplateSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateScheduleRepository extends JpaRepository<TemplateSchedule, String> {
}