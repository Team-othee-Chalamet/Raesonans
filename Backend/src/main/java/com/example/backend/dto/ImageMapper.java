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

        Boolean isSplash = false;

        if (image.getPlay() != null){
            if (image.getPlay().getSplashImage().equals(image)){
                isSplash = true;
            }
        }


        return new ImageDto(image.getId(), image.getGalleryVis(), isSplash, playTitle, image.getImagePath());
    }

    public static Image toEntity(ImageDto imageDto){
        Image newImage = new Image();

        newImage.setId(imageDto.id());
        newImage.setGalleryVis(imageDto.galleryVis());
        newImage.setImagePath(imageDto.url());

        return newImage;
    }

    public static Image infoToEntity(ImageInfoDto imageInfoDto){
        Image newImage = new Image();

        if (imageInfoDto.id() != null){
            newImage.setId(imageInfoDto.id());
        }

        newImage.setGalleryVis(imageInfoDto.galleryVis());

        return newImage;
    }
}
