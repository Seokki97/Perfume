package com.example.perfume.survey.domain;

import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import lombok.Getter;

@Getter
public enum SurveyType {

    GENDERLESS("남자 여자"),
    DEFAULT("캐쥬얼 포멀"),
    FOUR_SEASON("봄 여름 가을 겨울"),
    NOT_SELECT_SEASON("상관없음");
    private final String value;

    SurveyType(String value) {
        this.value = value;
    }

    public boolean isSeasonNotSelected(SurveyRequestDto surveyRequestDto) {
        return NOT_SELECT_SEASON.value.equals(surveyRequestDto.getSeasonAnswer());
    }
}
