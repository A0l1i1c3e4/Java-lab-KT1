package com.example.hits_java_kt1.Repositories;

import com.example.hits_java_kt1.Entities.PeriodSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodScheduleRepository extends JpaRepository<PeriodSchedule, String> {
}