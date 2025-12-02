package com.example.backend.dto;

import com.example.backend.model.Performance;

import java.util.ArrayList;
import java.util.List;

public class PerformanceMapper {

    public static List<PerformanceDto> toDtoList(List<Performance> performances){
        List<PerformanceDto> Dtos = new ArrayList<>();
        for(Performance p:performances){
            Dtos.add(toDto(p));
        }
        return Dtos;
    }

    public static PerformanceDto toDto(Performance performance){
        return new PerformanceDto();
    }

}
