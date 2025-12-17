package com.example.backend.dto;

import com.example.backend.model.Image;
import com.example.backend.model.Play;
import com.example.backend.service.PlayService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImageMapper {

    public static List<ImageDto> toDtoList(List<Image> images){
        List<ImageDto> dtos = new ArrayList<>();

        for (Image i: images){
            dtos.add(toDto(i));
        }

        return dtos;
    }

    public static ImageDto toDto(Image image){
        if (image == null){
            return null;
        }

        String playTitle = "";

        if (image.getPlay() != null){
            playTitle = image.getPlay().getTitle();
        }

        return new ImageDto(image.getGalleryVis(), image.getFrontPageVis(), playTitle, image.getImagePath());
    }

    public static Image toEntity(ImageDto imageDto){
        Image newImage = new Image();

        newImage.setGalleryVis(imageDto.galleryVis());
        newImage.setFrontPageVis(imageDto.frontPageVis());
        newImage.setImagePath(imageDto.url());

        return newImage;
    }

    public static Image infoToEntity(ImageInfoDto imageInfoDto){
        Image newImage = new Image();

        newImage.setGalleryVis(imageInfoDto.galleryVis());
        newImage.setFrontPageVis(imageInfoDto.frontPageVis());

        return newImage;
    }
}
