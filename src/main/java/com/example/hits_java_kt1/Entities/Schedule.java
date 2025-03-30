package com.example.hits_java_kt1.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "Schedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @Column(nullable = false, length = 32)
    private String id = UUID.randomUUID().toString().replace("-", "");
    @Column
    private String scheduleName;
    @Column(nullable = false)
    private Instant creationDate = Instant.now();
    @Column(nullable = false)
    private Instant updateDate = Instant.now();
}