package com.example.backend.dto;

public record LoginResponseDTO(String token,
                               UserDTO userDTO) {
}
