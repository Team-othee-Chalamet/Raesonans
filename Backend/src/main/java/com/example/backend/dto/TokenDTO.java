package com.example.backend.dto;

import com.example.backend.model.AppUser;

import java.time.LocalDateTime;

public record TokenDTO(Long id,
                       AppUser appUser,
                       String token,
                       LocalDateTime expiration) {
}
