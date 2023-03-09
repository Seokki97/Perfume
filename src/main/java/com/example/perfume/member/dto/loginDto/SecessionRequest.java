package com.example.perfume.member.dto.loginDto;

import lombok.Getter;

@Getter
public class SecessionRequest {
    private Long memberId;

    private String refreshToken;

    public SecessionRequest(Long memberId, String refreshToken){
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }
}
