package com.example.backend.repo;

import com.example.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayRepo extends JpaRepository<Image,Long> {
}
