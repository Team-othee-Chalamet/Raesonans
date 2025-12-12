package com.example.backend.controller;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.ImageInfoDto;
import com.example.backend.dto.MetaDto;
import com.example.backend.model.Image;
import com.example.backend.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postImage(@RequestPart("file") MultipartFile file, @RequestPart("meta") ImageInfoDto imageInfoDto){
        System.out.println("recieved an image");
        System.out.println(file.getOriginalFilename());

        try {
            imageService.saveImage(file, imageInfoDto);
            return ResponseEntity.ok("Success");
        }
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getImages(){
        return ResponseEntity.ok(imageService.getAllImageDtos());
    }

}
