package com.example.backend.service;

import com.example.backend.dto.PerformanceDto;
import com.example.backend.dto.PerformanceMapper;
import com.example.backend.model.Performance;
import com.example.backend.repo.PerformanceRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceService {
    private final PerformanceRepo performanceRepo;

    public PerformanceService(PerformanceRepo performanceRepo) {
        this.performanceRepo = performanceRepo;
    }

    public List<PerformanceDto> GetAllPerformances(){
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
}
