package com.example.backend.controller;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.MetaDto;
import com.example.backend.model.Image;
import com.example.backend.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postImage(@RequestPart("file") MultipartFile file, @RequestPart("meta") ImageDto imageDto){
        System.out.println("recieved an image");
        System.out.println(file.getOriginalFilename());
        System.out.println(imageDto.());

        imageService.saveImage(file, imageDto);

        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity<Image> getImages(){
        return ResponseEntity.ok(null);
    }

}
