package com.example.perfume.member.dto;

import com.example.perfume.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    private Long id;

    private String nickname;

    private String email;

    public MemberRequestDto() {
    }

    public MemberRequestDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .build();
    }

}
