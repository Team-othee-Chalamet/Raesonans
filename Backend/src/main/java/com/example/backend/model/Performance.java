package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Performance extends BaseEntity {
    LocalDate peformanceDate;
    LocalTime time;
    String ticketLink;

    @ManyToOne
    Play play;

    //Constructor

    public Performance(Play play, LocalDate peformanceDate, LocalTime time, String ticketLink) {
    //    this.play = play;
        this.peformanceDate = peformanceDate;
        this.time = time;
        this.ticketLink = ticketLink;
    }

    //Getters

    public Play getPlay() {
        return play;
    }

    public LocalDate getPeformanceDate() {
        return peformanceDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTicketLink() {
        return ticketLink;
    }

    //Setters

//    public void setPlay(Play play) {
//        this.play = play;
//    }

    public void setPeformanceDate(LocalDate peformanceDate) {
        this.peformanceDate = peformanceDate;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }
}
