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
    private String firstAnswerOfSurvey;

    @NotNull
    @Column(nullable = false, length = 100)
    private String secondAnswerOfSurvey;

    @NotNull
    @Column(nullable = false, length = 100)
    private String thirdAnswerOfSurvey;

    @NotNull
    @Column(nullable = false, length = 100)
    private String fourthAnswerOfSurvey;

    @NotNull
    @Column(nullable = false, length = 100)
    private String fifthAnswerOfSurvey;

    @Builder
    public Feature(Long id, String firstAnswerOfSurvey, String secondAnswerOfSurvey, String thirdAnswerOfSurvey, String fourthAnswerOfSurvey, String fifthAnswerOfSurvey) {
        this.id = id;
        this.firstAnswerOfSurvey = firstAnswerOfSurvey;
        this.secondAnswerOfSurvey = secondAnswerOfSurvey;
        this.thirdAnswerOfSurvey = thirdAnswerOfSurvey;
        this.fourthAnswerOfSurvey = fourthAnswerOfSurvey;
        this.fifthAnswerOfSurvey = fifthAnswerOfSurvey;
        //this.perfumeId = perfume.deliverPerfumeId();
    }
}
