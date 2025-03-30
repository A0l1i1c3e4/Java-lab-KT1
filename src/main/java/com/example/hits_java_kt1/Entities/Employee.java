package com.example.hits_java_kt1.Entities;

import com.example.hits_java_kt1.Enum.statusE;
import com.example.hits_java_kt1.Enum.positionE;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "Employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @Column(nullable = false, length = 32)
    private String id = UUID.randomUUID().toString().replace("-", "");
    @Column(nullable = false)
    private String employeeName;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private statusE status;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private positionE position = positionE.UNDEFINED;
}