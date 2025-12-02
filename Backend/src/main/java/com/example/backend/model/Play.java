package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Play extends BaseEntity {
    String title;
    String description;

    @OneToOne
    Image splashImage;

    @OneToMany (mappedBy = "play")
    List<Credit> credits;

    @OneToMany (mappedBy = "play")
    List<Image> images;

    @OneToMany (mappedBy = "play")
    List<Performance> performances;

    @OneToMany (mappedBy = "play")
    List<Review> reviews;

    //Constructors

    public Play(String title, List<Review> reviews, String description, List<Performance> performances, List<Credit> credits, List<Image> images) {
        this.title = title;
        this.reviews = reviews;
        this.credits = credits;
        this.description = description;
        this.performances = performances;
        this.splashImage = splashImage;
        this.images = images;
    }

    public Play() {}

    //Getters

    public String getTitle() {
        return title;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getDescription() {
        return description;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public List<Image> getImages() {
        return images;
    }

    public Image getSplashImage() {
        return splashImage;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    //Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }


    public void setSplashImage(Image splashImage) {
        this.splashImage = splashImage;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
