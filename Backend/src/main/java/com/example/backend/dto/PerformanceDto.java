package com.example.backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record PerformanceDto(Long id, String location, LocalDate performanceDate, LocalTime time, String ticketLink, PlayPreviewDto playPreviewDto) {
}
