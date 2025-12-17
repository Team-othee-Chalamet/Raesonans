package com.example.backend.dto;

import com.example.backend.model.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {

    public static List<ReviewDto> toDtoList(List<Review> reviews){
        List<ReviewDto> dtos = new ArrayList<>();
        if (reviews == null) return dtos;

        for (Review r: reviews){
            dtos.add(toDto(r));
        }
        return dtos;
    }

    public static ReviewDto toDto(Review review) {
        if (review == null) {
            return null;
        }

        // We map the fields from the Entity (review) to the Record (ReviewDto)
        return new ReviewDto(
                review.getId(),
                review.getMaxScore(),
                review.getActualScore(),
                review.getTitle(),
                review.getReviewText(),
                review.getSourceLink()
        );
    }

    public static Review toEntity(ReviewDto dto) {
        Review review = new Review();
        // Vi sætter ikke ID her, da databasen genererer det
        review.setTitle(dto.title());
        review.setActualScore(dto.actualScore());
        review.setMaxScore(dto.maxScore());
        review.setReviewText(dto.reviewText());
        review.setSourceLink(dto.sourceLink());

        return review;
    }
}
