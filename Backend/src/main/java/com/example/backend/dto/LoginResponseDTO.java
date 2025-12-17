package com.example.backend.dto;

public record LoginResponseDTO(String token,
                               AppUserDTO appUserDTO) {
}
