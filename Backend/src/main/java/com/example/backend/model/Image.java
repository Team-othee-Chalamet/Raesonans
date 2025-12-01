package com.example.backend.model;


public class Image {
    String imagePath;
    Boolean galleryVis;
    Boolean frontPageVis;

    //Constructor


    public Image(String imagePath, Boolean galleryVis, Boolean frontPageVis) {
        this.imagePath = imagePath;
        this.galleryVis = galleryVis;
        this.frontPageVis = frontPageVis;
    }

    //Getters


    public String getImagePath() {
        return imagePath;
    }

    public Boolean getGalleryVis() {
        return galleryVis;
    }

    public Boolean getFrontPageVis() {
        return frontPageVis;
    }

    //Setters

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setGalleryVis(Boolean galleryVis) {
        this.galleryVis = galleryVis;
    }

    public void setFrontPageVis(Boolean frontPageVis) {
        this.frontPageVis = frontPageVis;
    }
}
