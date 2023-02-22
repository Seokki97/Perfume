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
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private Long memberId;

    @NotNull
    private String nickname;
    @Column(length = 550)
    private String email;

    @Builder
    public Member(Long id, Long memberId, String nickname, String email) {
        this.id = id;
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;
    }

}
