package com.example.backend.dto;

import com.example.backend.model.User;

public class LoginMapper {

    static public UserDTO toUserDto(User user) {
        return new UserDTO(user.getUsername());
    }

}
