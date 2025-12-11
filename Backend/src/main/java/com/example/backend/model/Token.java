package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class Token extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "app_user_id")
    AppUser appUser;

    String token;
    LocalDateTime expiration;

    public Token(AppUser appUser, String token, LocalDateTime expiration) {
        this.appUser = appUser;
        this.token = token;
        this.expiration = expiration;
    }

    public Token() {
    }


    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}
