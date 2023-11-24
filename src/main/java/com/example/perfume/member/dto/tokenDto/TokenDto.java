package com.example.perfume.member.dto.tokenDto;

import lombok.Getter;

@Getter
public class TokenDto {

    private String accessToken;

    private String refreshToken;

    private TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
