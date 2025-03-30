package com.example.hits_java_kt1.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.OffsetTime;
import java.util.UUID;

@Entity
@Table(name = "SlotSchedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlotSchedule {
    @Id
    @Column(nullable = false, length = 32)
    private String id = UUID.randomUUID().toString().replace("-", "");
    @Column(nullable = false, length = 32)
    private String scheduleTemplateId;
    @Column(nullable = false)
    private OffsetTime beginTime;
    @Column(nullable = false)
    private OffsetTime endTime;
}