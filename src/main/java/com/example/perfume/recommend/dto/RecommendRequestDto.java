package com.example.perfume.recommend.dto;

import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import lombok.Getter;

@Getter
public class RecommendRequestDto {

    private SurveyRequestDto surveyRequestDto;

    private Recommender recommender;

    public RecommendRequestDto() {
    }

    public RecommendRequestDto(Recommender recommender, SurveyRequestDto surveyRequestDto) {
        this.recommender = recommender;
        this.surveyRequestDto = surveyRequestDto;
    }
}
