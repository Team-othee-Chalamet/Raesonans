package com.example.backend.service;

import com.example.backend.dto.PerformanceDto;
import com.example.backend.dto.PerformanceMapper;
import com.example.backend.model.Performance;
import com.example.backend.repo.PerformanceRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {
    private final PerformanceRepo performanceRepo;

    public PerformanceService(PerformanceRepo performanceRepo) {
        this.performanceRepo = performanceRepo;
    }

    public List<PerformanceDto> getAllPerformances(){
        List<Performance> foundPerformances = performanceRepo.findAll();

        List<PerformanceDto> returnPerformances = new ArrayList<>();
        for (Performance performance: foundPerformances){
            returnPerformances.add(PerformanceMapper.toDto(performance));
        }

        return returnPerformances;
    }

//    public List<Performance> GetNext5Performances(){
//
//        return upcomingPerformances;
//    }

    public PerformanceDto getPerformanceById(Long performanceId) {
        Optional<Performance> performance = performanceRepo.findById(performanceId);
        if (performance.isEmpty()) {
            throw new RuntimeException("Ingen forestilling fundet");
        }
        return PerformanceMapper.toDto(performance.get());
    }

    public PerformanceDto createPerformance(PerformanceDto performanceDto) {
        Performance performance = PerformanceMapper.toEntity(performanceDto);
        performance.setId(null);
        return PerformanceMapper.toDto(performanceRepo.save(performance));
    }

    public PerformanceDto editPerformance(Long performanceId, PerformanceDto performanceDto) {
        Optional<Performance> existingPerformance = performanceRepo.findById(performanceId);
        if (existingPerformance.isPresent()){
            Performance performance = PerformanceMapper.toEntity(performanceDto);
            Performance updatedPerformance = existingPerformance.get();
            updatedPerformance.setPerformanceDate(performance.getPerformanceDate());
            updatedPerformance.setLocation(performance.getLocation());
            updatedPerformance.setPlay(performance.getPlay());
            updatedPerformance.setTime(performance.getTime());
            return PerformanceMapper.toDto(performanceRepo.save(updatedPerformance));
        }

        throw new RuntimeException("Ingen forestilling fundet");
    }

    public void deletePerformance(Long performanceId) {
        performanceRepo.deleteById(performanceId);
    }
}
