package com.example.perfume.member.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false)
    private Long tokenId;

    @NotNull
    private String refreshToken;

    @NotNull
    private Long memberId;

    @Builder
    public Token(Long tokenId, String refreshToken, Long memberId) {
        this.tokenId = tokenId;
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }
}
