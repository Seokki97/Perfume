package com.example.perfume.survey.dto.featureDto;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import lombok.Getter;

@Getter
public class SurveyRequestDto {

    private Long id;

    private String firstAnswerOfSurvey;
    private String secondAnswerOfSurvey;
    private String thirdAnswerOfSurvey;
    private String fourthAnswerOfSurvey;
    private String fifthAnswerOfSurvey;

    public SurveyRequestDto(){

    }

    public SurveyRequestDto(Long id, String firstAnswerOfSurvey, String secondAnswerOfSurvey, String thirdAnswerOfSurvey, String fourthAnswerOfSurvey, String fifthAnswerOfSurvey) {
        this.id = id;
        this.firstAnswerOfSurvey = firstAnswerOfSurvey;
        this.secondAnswerOfSurvey = secondAnswerOfSurvey;
        this.thirdAnswerOfSurvey = thirdAnswerOfSurvey;
        this.fourthAnswerOfSurvey = fourthAnswerOfSurvey;
        this.fifthAnswerOfSurvey = fifthAnswerOfSurvey;
    }

    public Survey toEntity(Perfume perfume) {
        return Survey.builder()
                .id(id)
                .perfume(perfume)
                .firstAnswerOfSurvey(firstAnswerOfSurvey)
                .secondAnswerOfSurvey(secondAnswerOfSurvey)
                .thirdAnswerOfSurvey(thirdAnswerOfSurvey)
                .fourthAnswerOfSurvey(fourthAnswerOfSurvey)
                .fifthAnswerOfSurvey(fifthAnswerOfSurvey)
                .build();
    }
}