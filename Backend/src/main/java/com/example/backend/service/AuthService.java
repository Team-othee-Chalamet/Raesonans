package com.example.backend.service;

import com.example.backend.dto.LoginMapper;
import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.model.User;
import com.example.backend.repo.UserRepo;
import com.example.backend.util.PasswordHasher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    UserRepo userRepo;

    public AuthService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public LoginResponseDTO authenticateLogin(LoginRequestDTO loginRequestDTO) {
        System.out.println("AuthService: authenticateLogin");
        String username = loginRequestDTO.username();
        String password = loginRequestDTO.password();

        // Check if there is a user with that username
        Optional<User> optionalUser = userRepo.findByUsername((username));
        if(!optionalUser.isPresent()) {
            throw new RuntimeException("No user found with username: "+username);
        }

        // If user exists, check if password matches
        User foundUser = optionalUser.get();
        if(!PasswordHasher.hash(password).equals(foundUser.getPassword())) {
            throw new RuntimeException("Username and password does not match");
        }
        //Turn employee into a DTO
        UserDTO userDTO = LoginMapper.toUserDto(foundUser);
        // Generate a (simple "fake") token
        String returnToken = TokenService.generateToken();

        // Create the response and return it
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(returnToken, userDTO);

        return loginResponseDTO;
    }
}
