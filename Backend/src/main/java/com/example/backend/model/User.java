package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity {
    String username;
    String password;

    //Constructor

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

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
