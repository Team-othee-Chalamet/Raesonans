package com.example.backend.service;

import com.example.backend.model.Performance;
import com.example.backend.repo.PerformanceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {
    private final PerformanceRepo performanceRepo;

    public PerformanceService(PerformanceRepo performanceRepo) {
        this.performanceRepo = performanceRepo;
    }

    public List<Performance> GetAllPerformances(){
        return performanceRepo.findAll();
    }

//    public List<Performance> GetNext5Performances(){
//
//        return upcomingPerformances;
//    }
}
