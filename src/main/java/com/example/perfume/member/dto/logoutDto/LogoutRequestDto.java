package com.example.perfume.member.dto.logoutDto;

import lombok.Getter;

@Getter
public class LogoutRequestDto {

    private Long memberId;

    public LogoutRequestDto() {
    }

    public LogoutRequestDto(Long memberId) {
        this.memberId = memberId;
    }
}
