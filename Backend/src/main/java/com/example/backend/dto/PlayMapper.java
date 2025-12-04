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

    public static Play toEntity(PlayDto playDto){
        Play newPlay = new Play();
        newPlay.setTitle(playDto.title());
        newPlay.setDescription(playDto.description());

        //We are not setting the splash image here, because the user is adding this at a later stage

        //Adding credits using Synchronizing features
        for (CreditDto cDto: playDto.creditDtos()){
            newPlay.addCredit(CreditMapper.toEntity(cDto));
            System.out.println("Added a credit");
        }

        //Adding performances using Synchronizing features
        //NOTE: while not necessary at the moment, this will be nice if we make a
        //  feature letting users create a new play
        //  as they are creating a performance
        for (PerformanceDto pDto: playDto.performanceDtos()){
            newPlay.addPerformance(PerformanceMapper.toEntity(pDto));
        }

        //We are not adding reviews here, since they are added at a later stage

        //We are not adding images here, as that is done at a later stage

        return newPlay;
    }

}
