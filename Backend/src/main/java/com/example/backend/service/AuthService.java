package com.example.backend.service;

import com.example.backend.dto.*;
import com.example.backend.model.AppUser;
import com.example.backend.model.Token;
import com.example.backend.repo.AppUserRepo;
import com.example.backend.repo.TokenRepo;
import com.example.backend.util.Hasher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private final AppUserRepo appUserRepo;
    private final TokenService tokenService;
    private final TokenRepo tokenRepo;

    public AuthService(AppUserRepo appUserRepo, TokenService tokenService, TokenRepo tokenRepo){
        this.appUserRepo = appUserRepo;
        this.tokenService = tokenService;
        this.tokenRepo = tokenRepo;
    }

    @Transactional
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

        // If user has a token already, delete it
        Optional<Token> optionalToken = tokenRepo.findByAppUser(foundAppUser);
        if (optionalToken.isPresent()) {
            tokenRepo.deleteByAppUser(foundAppUser);
        }

        //Turn user into a DTO
        AppUserDTO appUserDTO = LoginMapper.toUserDto(foundAppUser);
        // Generate a token pair
        TokenPairDTO tokenPair = tokenService.generateTokenPair();
        // Create the response and attach clientToken
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(tokenPair.clientToken(), appUserDTO);

        TokenDTO tokenDTO = new TokenDTO(null, foundAppUser, tokenPair.hashedToken(), LocalDateTime.now().plusMinutes(31));
        System.out.println("Trying to save new token");
        tokenService.saveToken(tokenDTO);


        // TEST: Outcomment all and have it return LoginResponseDTO to check connection
        // LoginResponseDTO loginTestResponse = new LoginResponseDTO("Test", new UserDTO("Test"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return loginResponseDTO;
    }

    // Transactional allows for updates - setting expirations i method updates expiration in DB
    @Transactional
    public boolean validateToken(String authHeader) {

        // Bearer is added in fetchUtil (frontend)
        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer")) {
            return false;
        }

        // Turn token to bytes, then to hashed string, as token in repo is the hashed string
        String tokenString = authHeader.replace("Bearer ", "");
        byte[] tokenBytes = tokenService.stringToBytes(tokenString);
        tokenString = tokenService.hashToken(tokenBytes);

        // Find the token
        Optional<Token> foundToken = tokenRepo.findByToken(tokenString);
        if (!foundToken.isPresent()) {
            return false;
        }

        Token token = foundToken.get();

        if (token.getExpiration().isBefore(LocalDateTime.now()) ||
                token.getExpiration().isAfter(LocalDateTime.now().plusMinutes(31))) {
            tokenRepo.delete(token);
            return false;
        }

        // Update expiration
        token.setExpiration(LocalDateTime.now().plusMinutes(31));

        return true;
    }

    public void logOut(String authHeader) {
        System.out.println("Trying to logout");
        // Bearer is added in fetchUtil (frontend)
        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer")) {
            return;
        }

        // Turn token to bytes, then to hashed string, as token in repo is the hashed string
        String tokenString = authHeader.replace("Bearer ", "");
        byte[] tokenBytes = tokenService.stringToBytes(tokenString);
        tokenString = tokenService.hashToken(tokenBytes);

        // Find the token
        Optional<Token> foundToken = tokenRepo.findByToken(tokenString);
        if (!foundToken.isPresent()) {
            return;
        }

        Token token = foundToken.get();

        tokenRepo.delete(token);
    }
}