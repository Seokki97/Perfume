package com.example.perfume.member.dto.logoutDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LogoutRequestDto {

    private Long memberId;

    public LogoutRequestDto(){
    }
    @Builder
    public  LogoutRequestDto (Long memberId){
        this.memberId = memberId;
    }

}
