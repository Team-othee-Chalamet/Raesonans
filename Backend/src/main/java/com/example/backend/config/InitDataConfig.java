package com.example.backend.config;

import com.example.backend.model.Performance;
import com.example.backend.model.Play;
import com.example.backend.repo.PerformanceRepo;
import com.example.backend.repo.PlayRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
/*
@Configuration
public class InitDataConfig {

    @Bean
    @Profile("!test")
    CommandLineRunner loadTestData(
            PerformanceRepo performanceRepository,
            PlayRepo playRepository) {

        return args -> {

            performanceRepository.deleteAll();
            playRepository.deleteAll();

            if (performanceRepository.count() == 0) {

                // ----- LAV NOGLE TEST-PLAYS -----
                Play hamlet = new Play();
                hamlet.setTitle("Hamlet");
                Play macbeth = new Play();
                macbeth.setTitle("Macbeth");

                playRepository.saveAll(List.of(hamlet, macbeth));

                // ----- LAV PERFORMANCE DATA -----
                Performance perf1 = new Performance();
                perf1.setLocation("Århus teaterskole");
                perf1.setPerformanceDate(LocalDate.of(2025, 3, 12));
                perf1.setTime(LocalTime.of(19, 30));
                perf1.setTicketLink("https://ticketmaster.dk/event/hamlet1");
                perf1.setPlay(hamlet);

                Performance perf2 = new Performance();
                perf2.setLocation("Århus teaterskole");
                perf2.setPerformanceDate(LocalDate.of(2025, 3, 14));
                perf2.setTime(LocalTime.of(20, 0));
                perf2.setTicketLink("https://ticketmaster.dk/event/hamlet2");
                perf2.setPlay(hamlet);

                Performance perf3 = new Performance();
                perf3.setLocation("Århus teaterskole");
                perf3.setPerformanceDate(LocalDate.of(2025, 4, 2));
                perf3.setTime(LocalTime.of(18, 0));
                perf3.setTicketLink("https://ticketmaster.dk/event/macbeth1");
                perf3.setPlay(macbeth);

                performanceRepository.saveAll(List.of(perf1, perf2, perf3));

                System.out.println("Dummy Performance + Play data inserted!");
            }
        };
    }
}*/
