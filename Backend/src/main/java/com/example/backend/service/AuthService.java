package com.example.backend.service;

import com.example.backend.dto.*;
import com.example.backend.model.AppUser;
import com.example.backend.model.Token;
import com.example.backend.repo.AppUserRepo;
import com.example.backend.repo.TokenRepo;
import com.example.backend.util.Hasher;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
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

        TokenDTO tokenDTO = new TokenDTO(foundAppUser, tokenPair.hashedToken(), LocalDateTime.now().plusMinutes(31));
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
    public AppUser validateToken(String authHeader) {

        // Bearer is added in fetchUtil (frontend)
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            throw new RuntimeException("Invalid or missing token");
        }

        // Turn token to bytes, then to hashed string, as token in repo is the hashed string
        String tokenString = authHeader.replace("Bearer ", "");
        byte[] tokenBytes = tokenService.stringToBytes(tokenString);
        tokenString = tokenService.hashToken(tokenBytes);

        // Find the token
        Optional<Token> foundToken = tokenRepo.findByToken(tokenString);
        if (!foundToken.isPresent()) {
            throw new RuntimeException("Could not find token");
        }
        // Turn into DTO
        TokenDTO tokenDTO = TokenMapper.toDto(foundToken.get());

        // Delete if token has expired or expires in more than 31 minutes - in which case it is a mistake
        if (tokenDTO.expiration().isBefore(LocalDateTime.now()) ||
                tokenDTO.expiration().isAfter(LocalDateTime.now().plusMinutes(31))) {
            tokenRepo.delete(foundToken.get());
            throw new RuntimeException("Token has expired");
        }

        // Update expiration
        foundToken.get().setExpiration(LocalDateTime.now().plusMinutes(31));

        return tokenDTO.appUser();
    }
}