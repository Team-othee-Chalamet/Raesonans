package com.example.backend.repo;

import com.example.backend.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepo extends JpaRepository<Performer, Long> {
}
