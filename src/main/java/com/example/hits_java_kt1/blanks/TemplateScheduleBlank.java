package com.example.hits_java_kt1.blanks;

import jakarta.persistence.Column;
import lombok.*;
import java.time.Instant;

@Data
public class TemplateScheduleBlank {
    @Column(nullable = false, length = 2)
    private String templateType;
}
