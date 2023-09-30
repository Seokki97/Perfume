package com.example.perfume.member.dto.memberDto;

import com.example.perfume.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long memberId;

    private String nickname;

    private String email;

    public MemberResponseDto() {
    }

    public MemberResponseDto(Long memberId, String nickname, String email) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;

    }

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .email(email)
                .nickname(nickname)
                .build();
    }
}
