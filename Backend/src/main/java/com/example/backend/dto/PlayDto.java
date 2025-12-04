package com.example.backend.dto;

import java.util.List;

public record PlayDto(Long id,String title, String description, List<PerformanceDto> performanceDtos, List<CreditDto> creditDtos, ImageDto splashImg, List<ReviewDto> reviewDtos, List<ImageDto> imageDtos) {

}
