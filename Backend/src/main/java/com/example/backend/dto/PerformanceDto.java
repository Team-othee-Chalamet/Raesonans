package com.example.backend.dto;

import com.example.backend.model.Play;

import java.time.LocalDate;
import java.time.LocalTime;

public record PerformanceDto(Long id, LocalDate performanceDate, LocalTime time, String ticketLink, PlayPreviewDto playPreviewDto) {
}
