package com.example.perfume.member.dto.memberDto;

import com.example.perfume.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    private Long id;
    private Long memberId;
    private String nickname;

    private String email;

    public MemberRequestDto() {
    }

    @Builder
    public MemberRequestDto(Long id, Long memberId ,String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.memberId = memberId;
    }

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .email(email)
                .nickname(nickname)
                .build();
    }

}
