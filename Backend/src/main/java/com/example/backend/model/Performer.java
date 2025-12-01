package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class Performer extends BaseEntity {
    String name;
    String role;

    //Constructor

    public Performer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Performer() {}

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
