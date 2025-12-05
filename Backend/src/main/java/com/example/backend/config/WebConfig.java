package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // alle API endpoints under /api/
                        .allowedOrigins("http://127.0.0.1:5500") // din frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // tilladte HTTP-metoder
                        .allowedHeaders("*"); // tillad alle headers
            }
        };
    }
}