package com.example.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Review {
    int maxScore;
    int actualScore;
    String title;
    String reviewText;
    String sourceLink;

    //Constructor
    public Review(int maxScore, int actualScore, String title, String reviewText, String sourceLink) {
        this.maxScore = maxScore;
        this.actualScore = actualScore;
        this.title = title;
        this.reviewText = reviewText;
        this.sourceLink = sourceLink;
    }

    //Getters

    public int getMaxScore() {
        return maxScore;
    }

    public int getActualScore() {
        return actualScore;
    }

    public String getTitle() {
        return title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    //Setters

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }
}
