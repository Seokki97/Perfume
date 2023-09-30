package com.example.perfume.member.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;
    @NotNull
    private Long kakaoId;
    @NotNull
    private String nickname;

    @Column(length = 100)
    private String email;

    private String thumbnailImage;

    @Builder
    public Member(Long memberId, Long kakaoId, String nickname, String email, String thumbnailImage) {
        this.memberId = memberId;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
        this.thumbnailImage = thumbnailImage;
    }
}
