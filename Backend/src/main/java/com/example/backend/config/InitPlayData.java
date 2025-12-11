package com.example.backend.config;

import com.example.backend.model.*;
import com.example.backend.repo.CreditRepo;
import com.example.backend.repo.ImageRepo;
import com.example.backend.repo.PlayRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*@Component
@Profile("!test")
public class InitPlayData implements CommandLineRunner {
    private final ImageRepo imageRepo;
    PlayRepo playRepo;

    public InitPlayData(PlayRepo playRepo, ImageRepo imageRepo){
        this.playRepo = playRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}*/
