package com.example.perfume.member.domain;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "recommend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Builder
    public Recommend(Long id, Member member, Survey survey) {
        this.id = id;
        this.member = member;
        this.survey = survey;
    }

}
