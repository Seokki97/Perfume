package com.example.perfume.member.domain;

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
    @Column(name = "id", nullable = false)
    private Long id;

    private String nickname;

    private String email;

    private String refreshToken;

    @Builder
    public Member(Long id, String nickname, String email, String refreshToken){
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
