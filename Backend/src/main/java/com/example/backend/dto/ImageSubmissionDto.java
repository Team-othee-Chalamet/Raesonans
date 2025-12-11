package com.example.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public record ImageSubmissionDto(MultipartFile multipartFile) {
}
