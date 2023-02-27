package com.example.perfume.member.domain;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "recommend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Perfume> perfume;

    @Column(nullable = false,length = 40)
    private String recommender;

    @Column(length = 500)
    private String comment;

    @Builder
    public Recommendation(Long id, Member member, List<Perfume> perfume, String recommender, String comment) {
        this.id = id;
        this.member = member;
        this.perfume = perfume;
        this.recommender = recommender;
        this.comment = comment;
    }

}
