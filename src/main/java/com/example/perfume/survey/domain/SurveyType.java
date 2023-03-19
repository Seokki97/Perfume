package com.example.perfume.survey.domain;

import lombok.Getter;

@Getter
public enum SurveyType {

    GENDERLESS("젠더리스"),
    DEFAULT("디폴트"),
    FOUR_SEASON("무관");

    private final String value;

    SurveyType(String value) {
        this.value = value;
    }
}
