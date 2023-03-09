package com.example.perfume.member.dto.loginDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    private Long id;
    private String nickname;
    private String email;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponse(Long id, String nickname, String email, String accessToken, String refreshToken) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
