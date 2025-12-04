package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Play extends BaseEntity {
    String title;
    String description;
    String author;

//    @OneToMany (mappedBy = "play")
//    List<Image> images;

    @OneToMany (mappedBy = "play")
    List<Performance> performances;

    @OneToMany (mappedBy = "play")
    List<Review> reviews;

    //Constructors

    public Play(String title, List<Review> reviews, String description, List<Performance> performances, String author, List<Image> images) {
        this.title = title;
        this.reviews = reviews;
        this.description = description;
        this.performances = performances;
        this.author = author;
    //    this.splashImage = splashImage;
    //    this.images = images;
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

    public String getAuthor() {
        return author;
    }

//    public List<Image> getImages() {
//        return images;
//    }

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

    public void setAuthor(String author) {
        this.author = author;
    }

//    public void setSplashImage(Image splashImage) {
//        this.image = splashImage;
//    }

//    public void setImages(List<Image> images) {
//        this.images = images;
//    }
}
