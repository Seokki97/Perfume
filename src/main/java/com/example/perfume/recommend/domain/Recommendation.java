package com.example.perfume.recommend.domain;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "recommend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Perfume perfume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false, length = 40)
    private String recommender;

    @Column(length = 500)
    private String comment;

    private String scentAnswer;

    @Builder
    public Recommendation(Long id, Member member, Perfume perfume, String recommender, String comment, String scentAnswer) {
        this.id = id;
        this.member = member;
        this.perfume = perfume;
        this.recommender = recommender;
        this.comment = comment;
        this.scentAnswer = scentAnswer;
    }
}
