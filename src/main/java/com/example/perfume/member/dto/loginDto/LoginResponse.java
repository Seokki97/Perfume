package com.example.perfume.member.dto.loginDto;

import com.example.perfume.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    private Long memberId;
    private String nickname;
    private String email;
    private String thumbnailImage;

    private Long kakaoId;

    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponse(Long memberId, String nickname, String email, String accessToken, String refreshToken, String thumbnailImage, Long kakaoId) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;
        this.thumbnailImage = thumbnailImage;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.kakaoId = kakaoId;
    }

    public static LoginResponse makeLoginResponseObject(Member member, String accessToken, String refreshToken){
        return LoginResponse.builder()
                .memberId(member.getMemberId())
                .kakaoId(member.getKakaoId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .thumbnailImage(member.getThumbnailImage())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
