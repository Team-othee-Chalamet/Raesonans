package com.example.backend.repo;

import com.example.backend.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PerformanceRepo extends JpaRepository<Performance, Long> {
    List<Performance> findByPerformanceDateAfter(LocalDate today);
}
