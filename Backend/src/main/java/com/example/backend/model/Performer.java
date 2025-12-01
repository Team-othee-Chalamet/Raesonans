package com.example.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Performer {
    String name;
    String role;

    //Constructor

    public Performer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    //Getters


    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
