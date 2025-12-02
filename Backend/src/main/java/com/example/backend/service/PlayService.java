package com.example.backend.service;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.PlayDto;
import com.example.backend.dto.PlayMapper;
import com.example.backend.dto.PlayPreviewDto;
import com.example.backend.model.Play;
import com.example.backend.repo.PlayRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayService {

    private PlayRepo playRepo;

    public PlayService(PlayRepo playRepo){
        this.playRepo = playRepo;
    }

    public List<PlayPreviewDto> getPlayPreviews(){

        return PlayMapper.toPreviewDtoList(playRepo.findAll());
    }


    public List<PlayDto> getPlays(){

        return PlayMapper.toFullDtoList(playRepo.findAll());
    }
}
