package com.example.backend.service;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.ImageInfoDto;
import com.example.backend.dto.ImageMapper;
import com.example.backend.dto.MetaDto;
import com.example.backend.model.Image;
import com.example.backend.repo.ImageRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ImageService {

    private final PlayService playService;
    ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo, PlayService playService){
        this.imageRepo=imageRepo;
        this.playService = playService;
    }


    public void saveImage(MultipartFile file, ImageInfoDto imageInfoDto) throws IOException{
        //Saving file locally and getting the resulting URL for the database
        try {
            String url = saveFile(file);
            //Saving the entry in the database for later retrieval
            Image newImage = ImageMapper.infoToEntity(imageInfoDto);
            newImage.setImagePath(url);
            if (!imageInfoDto.playTitle().equalsIgnoreCase("")){
                playService.getPlayEntityFromTitle(imageInfoDto.playTitle()).addImage(newImage);
            }

            imageRepo.save(newImage);
        }
        catch (IOException e){
            throw e;
        }
    }

    String saveFile(MultipartFile file) throws IOException{

        Path imagePath = Paths.get("/tmp/images/" + file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){
            throw new IOException(e);
        }

        return "/images/" + file.getOriginalFilename();
    }

    public List<ImageDto> getAllImageDtos(){
        return ImageMapper.toDtoList(imageRepo.findAll());
    }
}
