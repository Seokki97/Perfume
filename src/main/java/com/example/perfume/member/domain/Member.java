package com.example.perfume.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Entity(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;
    @Column(name = "kakao_id", nullable = false)
    private Long kakaoId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(length = 100)
    private String email;

    private String thumbnailImage;

    @Builder
    private Member(Long memberId, Long kakaoId, String nickname, String email, String thumbnailImage) {
        this.memberId = memberId;
        this.kakaoId = Objects.requireNonNull(kakaoId, "카카오 ID는 필수입니다.");
        this.nickname = requireText(nickname);
        this.email = email;
        this.thumbnailImage = thumbnailImage;
    }

    public static Member create(Long kakaoId, String nickname, String email, String thumbnailImage) {
        return Member.builder()
                .kakaoId(kakaoId)
                .nickname(nickname)
                .email(email)
                .thumbnailImage(thumbnailImage)
                .build();
    }

    private static String requireText(String value) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException("닉네임은 비어 있을 수 없습니다.");
        }
        return value;
    }
}
