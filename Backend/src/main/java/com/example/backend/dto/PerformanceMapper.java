package com.example.backend.dto;

import com.example.backend.model.Performance;

public class PerformanceMapper {

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
