package com.example.backend.dto;

import com.example.backend.model.Image;
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
        return new ImageDto();
    }
}
