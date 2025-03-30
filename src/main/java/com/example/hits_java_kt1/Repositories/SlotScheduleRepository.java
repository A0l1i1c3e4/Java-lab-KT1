package com.example.hits_java_kt1.Repositories;

import com.example.hits_java_kt1.Entities.SlotSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.time.OffsetTime;

import java.util.List;

@Repository
public interface SlotScheduleRepository extends JpaRepository<SlotSchedule, String> {
    @Query("SELECT s FROM SlotSchedule s WHERE (:begin BETWEEN s.beginTime AND s.endTime OR :end BETWEEN s.beginTime AND s.endTime)")
    List<SlotSchedule> findOverlappingSlots(@Param("begin") OffsetTime begin, @Param("end") OffsetTime end);
}