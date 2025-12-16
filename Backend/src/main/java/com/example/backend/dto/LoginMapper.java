package com.example.backend.dto;

import com.example.backend.model.AppUser;

public class LoginMapper {

    static public AppUserDTO toUserDto(AppUser appUser) {
        return new AppUserDTO(appUser.getId(), appUser.getUsername());
    }

}
