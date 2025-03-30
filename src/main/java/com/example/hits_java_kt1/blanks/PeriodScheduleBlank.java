package com.example.hits_java_kt1.blanks;

import com.example.hits_java_kt1.Enum.slot_typeE;
import jakarta.persistence.Column;
import lombok.*;

@Data
public class PeriodScheduleBlank {
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
