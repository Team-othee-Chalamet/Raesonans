package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class AppUser extends BaseEntity {
    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    Token token;
    String username;
    String password;

    //Constructor

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser() {}

    //Getters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
