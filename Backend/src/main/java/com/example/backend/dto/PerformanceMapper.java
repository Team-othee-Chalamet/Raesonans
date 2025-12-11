package com.example.backend.dto;

import com.example.backend.model.Performance;
import com.example.backend.model.Play;

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
        Play play = performance.getPlay();
        return new PerformanceDto(performance.getId(), performance.getLocation(), performance.getPerformanceDate(), performance.getTime(), performance.getTicketLink(), PlayMapper.toPreviewDto(play));
    }

    public static Performance toEntity(PerformanceDto performanceDto){
        Performance performance = new Performance();
        performance.setId(performanceDto.id());
        performance.setLocation(performanceDto.location());
        performance.setPerformanceDate(performanceDto.performanceDate());
        performance.setTime(performanceDto.time());
        return performance;
    }
}
