package com.example.backend.dto;

public record ReviewDto(Long id,
                        int maxScore,
                        int actualScore,
                        String title,
                        String reviewText,
                        String sourceLink) {
}
