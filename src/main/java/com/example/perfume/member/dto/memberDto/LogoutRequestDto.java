package com.example.perfume.member.dto.memberDto;

import lombok.Getter;

@Getter
public class LogoutRequestDto {
    private String accessToken;
    private String refreshToken;

    public LogoutRequestDto(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
