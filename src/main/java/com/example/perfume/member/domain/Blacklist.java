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
    private Long id;

    @ManyToOne
    @NotNull
    private Member member;

    @NotNull
    private String accessToken;

    @Builder
    public Blacklist(Long id, Member member, String accessToken){
        this.id = id;
        this.member = member;
        this.accessToken = accessToken;
    }
}
