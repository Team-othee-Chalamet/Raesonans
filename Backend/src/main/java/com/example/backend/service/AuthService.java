package com.example.backend.service;

import com.example.backend.dto.LoginMapper;
import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.model.AppUser;
import com.example.backend.repo.AppUserRepo;
import com.example.backend.util.Hasher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    AppUserRepo appUserRepo;

    public AuthService(AppUserRepo appUserRepo){
        this.appUserRepo = appUserRepo;
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
        // Generate a (simple "fake") token
        String returnToken = TokenService.generateToken();
        // Create the response and return it
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(returnToken, userDTO);

        // TEST: Outcomment all and have it return LoginResponseDTO to check connection
        // LoginResponseDTO loginTestResponse = new LoginResponseDTO("Test", new UserDTO("Test"));

        return loginResponseDTO;
    }
}
