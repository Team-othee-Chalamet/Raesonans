package com.example.backend.model;

import com.example.backend.baseClasses.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Image extends BaseEntity {
    String imageUrl;
    Boolean galleryVis;
    Boolean frontPageVis;

    @OneToOne
    @JoinColumn(name = "splash_image_id")
    Image splashImage;

    @ManyToOne
    Play play;

    //Constructor

    public Image(String imageUrl, Boolean galleryVis, Boolean frontPageVis) {
        this.imageUrl = imageUrl;
        this.galleryVis = galleryVis;
        this.frontPageVis = frontPageVis;
    }

    public Image() {}

    //Getters


    public String getImagePath() {
        return imageUrl;
    }

    public Boolean getGalleryVis() {
        return galleryVis;
    }

    public Boolean getFrontPageVis() {
        return frontPageVis;
    }

    public Play getPlay() { return play; }

    //Setters

    public void setImagePath(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setGalleryVis(Boolean galleryVis) {
        this.galleryVis = galleryVis;
    }

    public void setFrontPageVis(Boolean frontPageVis) {
        this.frontPageVis = frontPageVis;
    }

    public void setPlay(Play play) { this.play = play; }
}
