package com.example.backend.config;

import com.example.backend.model.*;
import com.example.backend.repo.ImageRepo;
import com.example.backend.repo.PlayRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitPlayData implements CommandLineRunner {
    private final ImageRepo imageRepo;
    PlayRepo playRepo;

    public InitPlayData(PlayRepo playRepo, ImageRepo imageRepo){
        this.playRepo = playRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        playRepo.deleteAll();

        playRepo.save(
                new Play(
                        "Ridderland",
                        imageRepo.save(new Image()),
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
