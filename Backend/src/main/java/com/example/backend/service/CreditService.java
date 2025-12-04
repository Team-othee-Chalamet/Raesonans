package com.example.backend.service;

import com.example.backend.model.Credit;
import com.example.backend.repo.CreditRepo;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    private final CreditRepo creditRepo;

    public CreditService(CreditRepo creditRepo) {
        this.creditRepo = creditRepo;
    }

    public Credit getCreditById(Long id){
        return creditRepo.getById(id);
    }
}
