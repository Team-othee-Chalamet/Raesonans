package com.example.backend.dto;

import com.example.backend.model.Play;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayMapper {

    public static List<PlayPreviewDto> toPreviewDtoList(List<Play> plays){
        List<PlayPreviewDto> dtos = new ArrayList<>();

        for (Play p: plays){
            dtos.add(toPreviewDto(p));
        }

        return dtos;
    }

    public static List<PlayDto> toFullDtoList(List<Play> plays){
        List<PlayDto> dtos = new ArrayList<>();

        for (Play p: plays){
            dtos.add(toFullDto(p));
        }

        return dtos;
    }

    public static PlayPreviewDto toPreviewDto(Play play){
        return new PlayPreviewDto(
                play.getId(),
                play.getTitle(),
                play.getDescription(),
                ImageMapper.toDto(play.getSplashImage()));
    }

    public static PlayDto toFullDto(Play play){
        return new PlayDto(
                play.getId(),
                play.getTitle(),
                play.getDescription(),
                PerformanceMapper.toDtoList(play.getPerformances()),
                CreditMapper.toDtoList(play.getCredits()),
                ImageMapper.toDto(play.getSplashImage()),
                ReviewMapper.toDtoList(play.getReviews()),
                ImageMapper.toDtoList(play.getImages()));
    }
}
