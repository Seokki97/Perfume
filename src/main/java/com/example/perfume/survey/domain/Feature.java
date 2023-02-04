package com.example.perfume.survey.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "features")
@Table(name = "features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 100)
    private String answerOfSurvey;

  /*  @Column(nullable = false, length = 5)
    public Long perfumeId;*/

    @Builder
    public Feature(Long id, String answerOfSurvey) {
        this.id = id;
        this.answerOfSurvey = answerOfSurvey;
        //this.perfumeId = perfume.deliverPerfumeId();
    }
}
