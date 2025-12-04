package com.example.backend.dto;

import com.example.backend.model.Credit;
import com.example.backend.service.CreditService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditMapper {

    static CreditService creditService;

    public CreditMapper(CreditService creditService){
        this.creditService = creditService;
    }

    public static List<CreditDto> toDtoList(List<Credit> credits){
        List<CreditDto> dtos = new ArrayList<>();
        for(Credit c: credits){
            dtos.add(toDto(c));
        }
        return dtos;
    }

    public static CreditDto toDto(Credit credit){
        return new CreditDto(credit.getId(), credit.getRole(), credit.getName());
    }

    public static Credit toEntity(CreditDto creditDto){

        Credit newCredit;
        
        if (creditDto.id() != null){
            newCredit = creditService.getCreditById(creditDto.id());
        }else {
            newCredit = new Credit();
        }

        newCredit.setRole(creditDto.role());
        newCredit.setName(creditDto.name());

        return newCredit;
    }
}
