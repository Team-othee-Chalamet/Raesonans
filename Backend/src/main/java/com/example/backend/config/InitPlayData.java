package com.example.backend.config;

import com.example.backend.model.*;
import com.example.backend.repo.PlayRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitPlayData implements CommandLineRunner {
    PlayRepo playRepo;

    public InitPlayData(PlayRepo playRepo){
        this.playRepo = playRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        playRepo.deleteAll();

        playRepo.save(
                new Play(
                        "Ridderland",
                        List.of(
                                new Review(
                                        5,
                                        4,
                                        "Godt",
                                        "Mega fedt mand",
                                        "Sted.dk")
                        ),
                        "Et sejt teaterstykke om en mand på en and.",
                        new ArrayList<Performance>(),
                        List.of(new Credit(), new Credit()),
                        List.of(new Image(), new Image())
                        ));
    }
}
