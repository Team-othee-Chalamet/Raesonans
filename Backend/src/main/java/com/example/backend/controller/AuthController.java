package com.example.backend.controller;


import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            // Service checks if credentials match
            LoginResponseDTO loginResponseDTO = authService.authenticateLogin(loginRequestDTO);

            // Returns ResponseEntity with mapped key / value pairs for token and user
            return ResponseEntity.ok(Map.of(
                    "token", loginResponseDTO.token(),
                    "user", loginResponseDTO.userDTO()
            ));
            // If credentials don't match, returns an error message
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
