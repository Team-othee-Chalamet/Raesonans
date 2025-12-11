package com.example.backend.dto;

import com.example.backend.model.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {

    public static TokenDTO toDto(Token token) {
        return new TokenDTO(token.getAppUser(), token.getToken(), token.getExpiration());
    }

    public static Token toEntity(TokenDTO tokenDTO) {
        return new Token(tokenDTO.appUser(), tokenDTO.token(), tokenDTO.expiration());
    }
}
