package com.example.perfume.survey.dto.featureDto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Builder
@Getter
public class FeatureDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstAnswerOfSurvey;
    private String secondAnswerOfSurvey;
    private String thirdAnswerOfSurvey;
    private String fourthAnswerOfSurvey;
    private String fifthAnswerOfSurvey;


    // public Long perfumeId;

    public FeatureDto(Long id, String firstAnswerOfSurvey, String secondAnswerOfSurvey, String thirdAnswerOfSurvey, String fourthAnswerOfSurvey, String fifthAnswerOfSurvey) {
        this.id = id;
        this.firstAnswerOfSurvey = firstAnswerOfSurvey;
        this.secondAnswerOfSurvey = secondAnswerOfSurvey;
        this.thirdAnswerOfSurvey = thirdAnswerOfSurvey;
        this.fourthAnswerOfSurvey = fourthAnswerOfSurvey;
        this.fifthAnswerOfSurvey = fifthAnswerOfSurvey;
    }


    public FeatureDto() {

    }
}
