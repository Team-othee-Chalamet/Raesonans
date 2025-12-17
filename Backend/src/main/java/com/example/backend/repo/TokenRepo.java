package com.example.backend.repo;

import com.example.backend.model.AppUser;
import com.example.backend.model.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String token);

    Optional<Token> findByAppUser(AppUser appUser);

    @Modifying
    @Transactional
    @Query("delete from Token t where t.appUser = :user")
    void deleteByAppUser(AppUser user);
}
