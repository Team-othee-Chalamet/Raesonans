package com.example.backend.service;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.PlayDto;
import com.example.backend.dto.PlayMapper;
import com.example.backend.dto.PlayPreviewDto;
import com.example.backend.model.*;
import com.example.backend.repo.PlayRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayService {

    private final PlayMapper playMapper;
    private final PerformanceService performanceService;
    private PlayRepo playRepo;

    public PlayService(PlayRepo playRepo, PlayMapper playMapper, PerformanceService performanceService){
        this.playRepo = playRepo;
        this.playMapper = playMapper;
        this.performanceService = performanceService;
    }

    public List<PlayPreviewDto> getPlayPreviews(){

        return PlayMapper.toPreviewDtoList(playRepo.findAll());
    }


    public List<PlayDto> getPlays(){

        return PlayMapper.toFullDtoList(playRepo.findAll());
    }

    public PlayDto getPlayFromId(Long id){
        return PlayMapper.toFullDto(playRepo.getById(id));
    }

    public PlayDto saveNewPlay(PlayDto playDto){
        Play newPlay = PlayMapper.toEntity(playDto);

        newPlay.setId(null);

        Play dummyPlay = new Play();

        System.out.println(dummyPlay);
        System.out.println(newPlay);

        return playMapper.toFullDto(playRepo.save(newPlay));


    }

    public PlayDto updatePlay(PlayDto playDto, Long id){

        Play playToUpdate = playRepo.getById(id);
        Play updatedPlayInfo = PlayMapper.toEntity(playDto);

        cleanPlay(playToUpdate);

        populatePlay(playToUpdate, updatedPlayInfo);

        playRepo.save(playToUpdate);

        return PlayMapper.toFullDto(playToUpdate);
    }

    //This method cleans the play of any previous information, except for the ID.
    void cleanPlay(Play play){
        play.setTitle(null);
        play.setSplashImage(null);
        play.setDescription(null);
        play.removePerformances();
        play.removeCredits();
        play.removeImages();
        play.removeReviews();

    }

    //This method recieves two plays, and populates the first play with the information from the second play, while keeping the id of the first intact.
    void populatePlay(Play cleanPlay, Play playWithInfo){
        cleanPlay.setTitle(playWithInfo.getTitle());
        cleanPlay.setDescription(playWithInfo.getDescription());
        cleanPlay.setSplashImage(playWithInfo.getSplashImage());

        for (Performance p: playWithInfo.getPerformances()){
            cleanPlay.addPerformance(p);
        }
        for (Credit c: playWithInfo.getCredits()){
            cleanPlay.addCredit(c);
        }
        for (Image i: playWithInfo.getImages()){
            cleanPlay.addImage(i);
        }
        for (Review r: playWithInfo.getReviews()){
            cleanPlay.addReview(r);
        }
    }

    public void deletePlay(Long id){
        try{
            playRepo.deleteById(id);
        }
        catch (RuntimeException e){
            throw e;
        }
    }
}
