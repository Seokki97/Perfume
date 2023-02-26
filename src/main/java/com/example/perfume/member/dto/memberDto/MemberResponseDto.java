package com.example.perfume.member.dto.memberDto;

import com.example.perfume.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;

    private String nickname;

    private String email;

    public MemberResponseDto() {
    }

    public MemberResponseDto(Long id, String nickname, String email) {
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
