package com.example.backend.service;

import com.example.backend.dto.ImageDto;
import com.example.backend.dto.ImageMapper;
import com.example.backend.dto.MetaDto;
import com.example.backend.repo.ImageRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo){
        this.imageRepo=imageRepo;
    }


    public void saveImage(MultipartFile file, ImageDto imageDto){
        //Saving file locally and getting the resulting URL for the database
        String url = saveFile(file);

        //Saving the entry in the database for later retrieval
        imageRepo.save(ImageMapper.toEntity(imageDto));
    }

    String saveFile(MultipartFile file){

        return "dummyURL";
    }
}
