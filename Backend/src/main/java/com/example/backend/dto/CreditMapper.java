package com.example.backend.dto;

import com.example.backend.model.Credit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditMapper {

    public static List<CreditDto> toDtoList(List<Credit> credits){
        List<CreditDto> dtos = new ArrayList<>();
        for(Credit c: credits){
            dtos.add(toDto(c));
        }
        return dtos;
    }

    public static CreditDto toDto(Credit credit){
        return new CreditDto();
    }
}
