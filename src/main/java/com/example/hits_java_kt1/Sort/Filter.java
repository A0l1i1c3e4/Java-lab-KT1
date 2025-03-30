package com.example.hits_java_kt1.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private String id;
    private String slotId;
    private String scheduleId;
    private String slotType;
    private String administratorId;
    private String executorId;
}