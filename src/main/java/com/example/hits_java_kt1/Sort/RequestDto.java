package com.example.hits_java_kt1.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Filter filter;
    private Sort sort;
    private Integer page;
}