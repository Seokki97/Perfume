package com.example.perfume.member.dto;

import com.example.perfume.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;

    private String nickname;

    private String email;

    private String refreshToken;


    public MemberResponseDto(){
    }

    public MemberResponseDto(Long id, String nickname, String email, String refreshToken){
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.refreshToken = refreshToken;
    }

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .refreshToken(refreshToken)
                .build();
    }

}
