package com.example.backend.controller;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.PlayDto;
import com.example.backend.dto.PlayPreviewDto;
import com.example.backend.service.PlayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plays")
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @GetMapping
    public ResponseEntity<List<PlayDto>> getPlays(){
        List<PlayDto> returnList = playService.getPlays();

        return ResponseEntity.ok(returnList);
    }

    @GetMapping("/preview")
    public ResponseEntity<List<PlayPreviewDto>> getPlayPreview(){
        List<PlayPreviewDto> returnList = playService.getPlayPreviews();

        return ResponseEntity.ok(returnList);
    }
}