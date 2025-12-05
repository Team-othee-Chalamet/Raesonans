package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Performance extends BaseEntity implements Comparable<Performance> {
    String location;
    LocalDate performanceDate;
    LocalTime time;
    String ticketLink;

    @ManyToOne
    Play play;

    //Constructor

    public Performance(){}

    public Performance(Play play, String location, LocalDate peformanceDate, LocalTime time, String ticketLink) {
        this.play = play;
        this.location = location;
        this.performanceDate = peformanceDate;
        this.time = time;
        this.ticketLink = ticketLink;
    }

    //Getters

    public Play getPlay() {
        return play;
    }

    public LocalDate getPerformanceDate() {
        return performanceDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTicketLink() {
        return ticketLink;
    }

    public String getLocation() {
        return location;
    }

    //Setters

    public void setPlay(Play play) { this.play = play; }

    public void setLocation(String location) { this.location = location; }

    public void setPerformanceDate(LocalDate performanceDate) {
        this.performanceDate = performanceDate;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }

    @Override
    public int compareTo(Performance other) {
        return this.performanceDate.compareTo(other.performanceDate);
    }
}
