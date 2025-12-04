package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Play extends BaseEntity {
    String title;
    String description;

    @OneToOne
    @JoinColumn(name = "splash_image_id")
    private Image splashImage;

    @OneToMany (mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Credit> credits;

    @OneToMany (mappedBy = "play", cascade = CascadeType.ALL)
    List<Image> images;

    @OneToMany (mappedBy = "play", cascade = CascadeType.ALL)
    List<Performance> performances;

    @OneToMany (mappedBy = "play", cascade = CascadeType.ALL)
    List<Review> reviews;

    //Constructors

    public Play(String title, Image splashImage, List<Review> reviews, String description, List<Performance> performances, List<Credit> credits, List<Image> images) {
        this.title = title;
        this.reviews = reviews;
        this.credits = credits;
        this.description = description;
        this.performances = performances;
        this.splashImage = splashImage;
        this.images = images;
    }

    public Play() {
        this.credits = new ArrayList<>();
        this.images = new ArrayList<>();
        this.performances = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

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

    // Synchronizing methods for lists in play
    public void addImage(Image image){
        this.images.add(image);
        image.setPlay(this);
    }

    public void addCredit(Credit credit){
        this.credits.add(credit);
        credit.setPlay(this);
    }

    public void addPerformance(Performance performance){
        this.performances.add(performance);
        performance.setPlay(this);
    }

    public void addReview(Review review){
        this.reviews.add(review);
        review.setPlay(this);
    }

    //Removal synchronizing functions
    public void removeCredits(){
        for (Credit c: credits){
            c.setPlay(null);
        }
        credits.clear();
    }

    public void removeReviews(){
        for (Review r: reviews){
            r.setPlay(null);
        }
        reviews.clear();
    }

    public void removePerformances(){
        for (Performance p: performances){
            p.setPlay(null);
        }
        performances.clear();
    }

    public void removeImages(){
        for (Image i: images){
            i.setPlay(null);
        }
        images.clear();
    }

    //ToString

    @Override
    public String toString() {
        return "Play{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", splashImage=" + splashImage +
                ", credits=" + credits +
                ", images=" + images +
                ", performances=" + performances +
                ", reviews=" + reviews +
                '}';
    }
}
