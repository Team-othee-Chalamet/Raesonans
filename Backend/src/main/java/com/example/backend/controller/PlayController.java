package com.example.backend.controller;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.PlayDto;
import com.example.backend.dto.PlayPreviewDto;
import com.example.backend.service.PlayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plays")
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

    @GetMapping("/{id}")
    public ResponseEntity<PlayDto> getPlayById(@PathVariable Long id){
        return ResponseEntity.ok(playService.getPlayFromId(id));
    }


    @PostMapping
    public ResponseEntity<PlayDto> postPlay(@RequestBody PlayDto playDto){
        try{
            System.out.println("Recieved a play Post request. " + playDto);
            return ResponseEntity.ok(playService.saveNewPlay(playDto));
        }
        catch (RuntimeException e){
            System.out.println("We encountered an error");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayDto> putPlay(@RequestBody PlayDto playDto, @PathVariable Long id){
        try{
            System.out.println("Recieved a play put request. " + playDto);
            return ResponseEntity.ok(playService.updatePlay(playDto, id));
        }
        catch (RuntimeException e){
            System.out.println("We encountered an error while updating the play");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlayDto> deletePlay(@PathVariable Long id){
        try{
            System.out.println("Recieved a play delete request. " + id);
            playService.deletePlay(id);
            return ResponseEntity.ok(null);
        }
        catch (RuntimeException e){
            System.out.println("We encountered an error while deleting the play");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}