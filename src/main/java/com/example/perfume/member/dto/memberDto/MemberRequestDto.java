package com.example.perfume.member.dto.memberDto;

import com.example.perfume.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    private Long memberId;
    private Long kakaoId;
    private String nickname;

    private String email;

    private String thumbnailImage;

    public MemberRequestDto() {
    }

    @Builder
    public MemberRequestDto(Long memberId, Long kakaoId, String nickname, String email, String thumbnailImage) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;
        this.kakaoId = kakaoId;
        this.thumbnailImage = thumbnailImage;
    }

    public Member toEntity() {
        return Member.create(kakaoId, nickname, email, thumbnailImage);
    }
}
