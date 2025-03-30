package com.example.hits_java_kt1.blanks;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.OffsetTime;

@Data
public class SlotScheduleBlank {
    @Column(nullable = false, length = 32)
    private String scheduleTemplateId;
    @Schema(description = "Время начала слота", example = "12:38:09+00:00")
    @NotBlank(message = "Время начала слота не должен быть пустым.")
    private OffsetTime beginTime;
    @Schema(description = "Время завершения слота", example = "12:38:09+00:00")
    @NotBlank(message = "Время завершения слота не должен быть пустым.")
    private OffsetTime endTime;
}
