package com.example.backend.repo;

import com.example.backend.model.Image;
import com.example.backend.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayRepo extends JpaRepository<Play,Long> {
}
