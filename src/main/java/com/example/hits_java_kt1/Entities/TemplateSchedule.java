package com.example.hits_java_kt1.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "TemplateSchedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TemplateSchedule {
    @Id
    @Column(nullable = false, length = 32)
    private String id = UUID.randomUUID().toString().replace("-", "");
    @Column(nullable = false)
    private Instant creationDate = Instant.now();
    @Column(nullable = false, length = 2)
    private String templateType;
}