//package com.example.backend.config;
//
//import com.example.backend.model.AppUser;
//import com.example.backend.repo.AppUserRepo;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class InitUserConfig {
//
//    @Bean
//    CommandLineRunner loadUserTestData(
//            AppUserRepo appUserRepo
//    ) {
//        return args -> {
//            appUserRepo.deleteAll();
//
//            if (appUserRepo.count() == 0) {
//                AppUser Peter = new AppUser("Peter", "Test");
//                AppUser Frederik = new AppUser("Frederik", "Test");
//                AppUser Sarah = new AppUser("Sarah", "Test");
//                AppUser Mikkel = new AppUser("Mikkel", "Test");
//
//                appUserRepo.saveAll(List.of(Peter, Frederik, Sarah, Mikkel));
//            }
//        };
//    }
//}
