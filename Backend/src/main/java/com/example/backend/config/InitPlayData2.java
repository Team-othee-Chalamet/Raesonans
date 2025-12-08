package com.example.backend.config;

import com.example.backend.model.Play;
import com.example.backend.repo.PlayRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class InitPlayData2 implements CommandLineRunner {
    private final PlayRepo playRepository;

    public InitPlayData2(PlayRepo playRepository) {
        this.playRepository = playRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Play p1 = new Play();
        p1.setTitle("Hamlet");
        p1.setDescription("A Shakespeare tragedy about revenge and madness.");

        Play p2 = new Play();
        p2.setTitle("Macbeth");
        p2.setDescription("A dark tale of ambition, fate, and consequences.");

        Play p3 = new Play();
        p3.setTitle("The Importance of Being Earnest");
        p3.setDescription("Oscar Wilde’s witty comedy of mistaken identities.");

        playRepository.save(p1);
        playRepository.save(p2);
        playRepository.save(p3);

        System.out.println("✔ Dummy Play data initialized with CommandLineRunner.");
    }
}
