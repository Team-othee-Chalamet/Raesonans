package com.example.backend.repo;

import com.example.backend.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditRepo extends JpaRepository<Credit, Long> {
}
