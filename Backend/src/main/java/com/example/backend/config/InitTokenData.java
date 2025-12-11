package com.example.backend.config;

import com.example.backend.repo.TokenRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitTokenData {

    CommandLineRunner removeAllToken (TokenRepo tokenRepo) {
        return args -> {
            tokenRepo.deleteAll();
        };
    }
}
