package com.example.backend.controller;

import com.example.backend.dto.PerformanceDto;
import com.example.backend.service.PerformanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {
    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping
    public ResponseEntity<List<PerformanceDto>> getAllPerformances() {
        System.out.println(performanceService.getAllPerformances());
        return ResponseEntity.ok(performanceService.getAllPerformances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDto> getPerformanceById(@PathVariable Long performanceId) {
        return ResponseEntity.ok(performanceService.getPerformanceById(performanceId));
    }

    @PostMapping
    public ResponseEntity<PerformanceDto> createPerformance(@RequestBody PerformanceDto performanceDto){
        return ResponseEntity.ok(performanceService.createPerformance(performanceDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceDto> updatePerformance(@PathVariable Long performanceId, @RequestBody PerformanceDto performanceDto) {
        try {
            return ResponseEntity.ok(performanceService.editPerformance(performanceId, performanceDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerformanceById(@PathVariable Long performanceId) {
        performanceService.deletePerformance(performanceId);
    }
}
