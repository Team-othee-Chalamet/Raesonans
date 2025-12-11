package com.example.backend.repo;

import com.example.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String token);
}
