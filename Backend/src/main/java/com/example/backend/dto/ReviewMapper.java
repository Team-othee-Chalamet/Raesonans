package com.example.backend.dto;

import com.example.backend.model.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {

    public static List<ReviewDto> toDtoList(List<Review> reviews){
        List<ReviewDto> dtos = new ArrayList<>();

        for (Review r: reviews){
            dtos.add(toDto(r));
        }
        return dtos;
    }

    public static ReviewDto toDto(Review review){
        return new ReviewDto();
    }

}
