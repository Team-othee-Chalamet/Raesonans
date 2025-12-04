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
        return new PerformanceDto(performance.getId(), performance.getPerformanceDate(), performance.getTime(), performance.getTicketLink(), performance.getPlay());
    }

    public static Performance toEntity(PerformanceDto performanceDto){
        Performance performance = new Performance();
        performance.setId(performanceDto.id());
        performance.setPerformanceDate(performanceDto.performanceDate());
        performance.setPerformanceDate(performanceDto.performanceDate());
        performance.setTime(performanceDto.time());
        performance.setPlay(performanceDto.play());
        return performance;
    }
}
