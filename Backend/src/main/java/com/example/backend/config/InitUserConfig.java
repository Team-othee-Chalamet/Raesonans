package com.example.backend.config;

import com.example.backend.model.User;
import com.example.backend.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitUserConfig {

    @Bean
    CommandLineRunner loadUserTestData(
            UserRepo userRepo
    ) {
        return args -> {
            userRepo.deleteAll();

            if (userRepo.count() == 0) {
                User Peter = new User("Peter", "Test");
                User Frederik = new User ("Frederik", "Test");
                User Sarah = new User ("Sarah", "Test");
                User Mikkel = new User ("Mikkel", "Test");

                userRepo.saveAll(List.of(Peter, Frederik, Sarah, Mikkel));
            }
        };
    }
}
