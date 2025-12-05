package com.example.backend.dto;

import com.example.backend.model.AppUser;

public class LoginMapper {

    static public UserDTO toUserDto(AppUser appUser) {
        return new UserDTO(appUser.getUsername());
    }

}
