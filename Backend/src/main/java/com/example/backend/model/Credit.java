package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Credit extends BaseEntity {

    String role;
    String name;

    @ManyToOne
    Play play;


}
