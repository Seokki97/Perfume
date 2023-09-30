package com.example.perfume.member.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "blacklist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blacklist_id", nullable = false)
    private Long blacklistId;

    @ManyToOne
    @NotNull
    private Member member;

    @NotNull
    private String accessToken;

    @Builder
    public Blacklist(Long blacklistId, Member member, String accessToken) {
        this.blacklistId = blacklistId;
        this.member = member;
        this.accessToken = accessToken;
    }
}
