package com.example.backend.controller;

import com.example.backend.dto.PerformanceDto;
import com.example.backend.model.Performance;
import com.example.backend.service.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {
    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping
    public ResponseEntity<List<PerformanceDto>> GetAllPerformances() {
        System.out.println(performanceService.GetAllPerformances());
        return ResponseEntity.ok(performanceService.GetAllPerformances());
    }
}
