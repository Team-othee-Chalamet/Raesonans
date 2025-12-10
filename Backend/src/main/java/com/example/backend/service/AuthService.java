package com.example.backend.service;

import com.example.backend.dto.*;
import com.example.backend.model.AppUser;
import com.example.backend.model.Token;
import com.example.backend.repo.AppUserRepo;
import com.example.backend.util.Hasher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private AppUserRepo appUserRepo;
    private final TokenService tokenService;

    public AuthService(AppUserRepo appUserRepo, TokenService tokenService){
        this.appUserRepo = appUserRepo;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO authenticateLogin(LoginRequestDTO loginRequestDTO) {
        System.out.println("AuthService: authenticateLogin");
        String username = loginRequestDTO.username();
        String password = loginRequestDTO.password();

        // Check if there is a user with that username
        Optional<AppUser> optionalUser = appUserRepo.findByUsername((username));
        if(!optionalUser.isPresent()) {
            throw new RuntimeException("No user found with username: "+username);
        }

        // If user exists, check if password matches
        AppUser foundAppUser = optionalUser.get();
        if(!Hasher.hashPassword(password).equals(foundAppUser.getPassword())) {
            throw new RuntimeException("Username and password does not match");
        }

        //Turn user into a DTO
        UserDTO userDTO = LoginMapper.toUserDto(foundAppUser);
        // Generate a token pair
        TokenPairDTO tokenPair = tokenService.generateTokenPair();
        // Create the response and attach clientToken
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(tokenPair.clientToken(), userDTO);

        Token token = new Token(foundAppUser, tokenPair.hashedToken(), LocalDateTime.now().plusHours(1));

        // TEST: Outcomment all and have it return LoginResponseDTO to check connection
        // LoginResponseDTO loginTestResponse = new LoginResponseDTO("Test", new UserDTO("Test"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return loginResponseDTO;
    }
}