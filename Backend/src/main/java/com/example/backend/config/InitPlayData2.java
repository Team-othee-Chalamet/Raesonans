package com.example.backend.config;

import com.example.backend.model.Credit;
import com.example.backend.model.Play;
import com.example.backend.model.Review;
import com.example.backend.repo.PlayRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class InitPlayData2 implements CommandLineRunner {
    private final PlayRepo playRepository;

    public InitPlayData2(PlayRepo playRepository) {
        this.playRepository = playRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // --- PLAY 1: HAMLET ---
        Play p1 = new Play();
        p1.setTitle("Hamlet");
        p1.setDescription("A Shakespeare tragedy about revenge and madness.");

        // Add Reviews to Hamlet
        // Constructor: (maxScore, actualScore, title, text, link)
        p1.addReview(new Review(6, 5, "Mesterværk", "En utrolig intens oplevelse med fantastisk skuespil.", "https://politiken.dk"));
        p1.addReview(new Review(6, 6, "Klassiker", "Det bliver ikke bedre end dette. Scenografien var i top.", "https://berlingske.dk"));

        // Add Credits to Hamlet
        // Constructor: (role, name)
        p1.addCredit(new Credit("Instruktør", "Peter Langdal"));
        p1.addCredit(new Credit("Hovedrolle", "Laurence Olivier"));
        p1.addCredit(new Credit("Scenograf", "Maja Ravn"));


        // --- PLAY 2: MACBETH ---
        Play p2 = new Play();
        p2.setTitle("Macbeth");
        p2.setDescription("A dark tale of ambition, fate, and consequences.");

        // Add Review to Macbeth
        p2.addReview(new Review(10, 8, "Mørk og dyster", "En meget blodig fortolkning af klassikeren.", "https://teateravisen.dk"));

        // Add Credits to Macbeth
        p2.addCredit(new Credit("Instruktør", "Katrine Wiedemann"));
        p2.addCredit(new Credit("Lysdesign", "Jesper Kongshaug"));


        // --- PLAY 3: EARNEST ---
        Play p3 = new Play();
        p3.setTitle("The Importance of Being Earnest");
        p3.setDescription("Oscar Wilde’s witty comedy of mistaken identities.");
        // No reviews or credits for this one, to test empty state


        // Save all (CascadeType.ALL ensures reviews and credits are saved automatically)
        playRepository.save(p1);
        playRepository.save(p2);
        playRepository.save(p3);

        System.out.println("✔ Dummy Play data initialized with Reviews and Credits.");
    }
}