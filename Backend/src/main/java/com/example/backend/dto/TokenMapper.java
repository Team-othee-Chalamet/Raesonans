package com.example.backend.dto;

import com.example.backend.model.Token;
import com.example.backend.service.TokenService;
import org.springframework.stereotype.Component;


@Component
public class TokenMapper {

    static TokenService tokenService;

    public TokenMapper(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public static TokenDTO toDto(Token token) {
        return new TokenDTO(token.getId(), token.getAppUser(), token.getToken(), token.getExpiration());
    }

    public static Token toEntity(TokenDTO tokenDTO) {
        Token token;

        if (tokenDTO.id() != null){
            token = tokenService.getTokenById(tokenDTO.id());
        } else {
            token = new Token(tokenDTO.appUser(), tokenDTO.token(), tokenDTO.expiration());
        }
        return token;
    }
}
