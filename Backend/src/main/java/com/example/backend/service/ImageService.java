package com.example.backend.service;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.ImageInfoDto;
import com.example.backend.dto.ImageMapper;
import com.example.backend.dto.MetaDto;
import com.example.backend.model.Image;
import com.example.backend.model.Play;
import com.example.backend.repo.ImageRepo;
import com.example.backend.repo.PlayRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private final PlayService playService;
    private final PlayRepo playRepo;
    ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo, PlayService playService, PlayRepo playRepo){
        this.imageRepo=imageRepo;
        this.playService = playService;
        this.playRepo = playRepo;
    }


    public void saveImage(MultipartFile file, ImageInfoDto imageInfoDto) throws IOException{
        //Saving file locally and getting the resulting URL for the database
        try {
            String url = saveFile(file);
            //Saving the entry in the database for later retrieval
            Image newImage = ImageMapper.infoToEntity(imageInfoDto);
            newImage.setId(null);
            newImage.setImagePath(url);

            imageRepo.save(newImage);

            if (!imageInfoDto.playTitle().equalsIgnoreCase("")){
                playService.getPlayEntityFromTitle(imageInfoDto.playTitle()).addImage(newImage);

                if (imageInfoDto.setSplash()){
                    Play play = newImage.getPlay();
                    play.setSplashImage(newImage);
                    playRepo.save(play);
                }
            }


        }
        catch (IOException e){
            throw e;
        }
    }

    public ImageDto updateImage(ImageInfoDto imageInfoDto){
        //Get old image info from the database, based on the imageInfoDtos ID
        Image imageToUpdate = imageRepo.getById(imageInfoDto.id());

        //Then set all the old info to be the new info.
        imageToUpdate.setPlay(playService.getPlayEntityFromTitle(imageInfoDto.playTitle()));
        imageToUpdate.setGalleryVis(imageInfoDto.galleryVis());

        imageRepo.save(imageToUpdate);

        //If this is the new splash image for the play, ensure synchronicity
        if (imageInfoDto.setSplash()){
            Play play = imageToUpdate.getPlay();
            play.setSplashImage(imageToUpdate);
            playRepo.save(play);
        }

        //Then save and return the image.

        return ImageMapper.toDto(imageToUpdate);
    }

    String saveFile(MultipartFile file) throws IOException{

        Path imagePath = Paths.get("/tmp/images/" + file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){
            throw new IOException(e);
        }

        return "/image/" + file.getOriginalFilename();
    }

    public List<ImageDto> getAllImageDtos(){
        return ImageMapper.toDtoList(imageRepo.findAll());
    }

    public void deleteImageWithId(Long id){
        Image imageToDelete = imageRepo.getById(id);
        if (imageToDelete.getPlay().getSplashImage().equals(imageToDelete)){
            Play play = imageToDelete.getPlay();
            play.setSplashImage(null);
            playRepo.save(play);
        }

        imageRepo.deleteById(id);
    }
}
