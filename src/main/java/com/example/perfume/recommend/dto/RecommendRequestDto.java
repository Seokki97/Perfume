package com.example.perfume.recommend.dto;

import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import lombok.Getter;

@Getter
public class RecommendRequestDto {

    private SurveyRequestDto surveyAnswers;

    private String recommender;

    private Long recommendedMemberId;

    private String comment;

    public RecommendRequestDto() {
    }

    public RecommendRequestDto(SurveyRequestDto surveyAnswers, Long recommendedMemberId, String comment,
                               String recommender) {
        this.surveyAnswers = surveyAnswers;
        this.recommender = recommender;
        this.recommendedMemberId = recommendedMemberId;
        this.comment = comment;
    }

    public String getScentAnswer() {
        return surveyAnswers.getScentAnswer();
    }
}
