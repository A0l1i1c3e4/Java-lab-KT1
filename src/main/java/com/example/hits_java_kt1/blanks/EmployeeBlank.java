package com.example.hits_java_kt1.blanks;

import com.example.hits_java_kt1.Enum.positionE;
import com.example.hits_java_kt1.Enum.statusE;
import jakarta.persistence.*;
import lombok.*;

@Data
public class EmployeeBlank {
    @Column(nullable = false)
    private String employeeName;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private statusE status;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private positionE position = positionE.UNDEFINED;
}