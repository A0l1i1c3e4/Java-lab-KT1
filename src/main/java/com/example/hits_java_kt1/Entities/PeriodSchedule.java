package com.example.hits_java_kt1.Entities;

import com.example.hits_java_kt1.Enum.slot_typeE;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "PeriodSchedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodSchedule {
    @Id
    @Column(nullable = false, length = 32)
    private String id = UUID.randomUUID().toString().replace("-", "");
    @Column(nullable = false, length = 32)
    private String slotId;
    @Column(nullable = false, length = 32)
    private String scheduleId;
    @Column(nullable = false, length = 20)
    private slot_typeE slotType = slot_typeE.UNDEFINED;
    @Column(nullable = false, length = 32)
    private String administratorId;
    @Column(length = 32)
    private String executorId;
}