package com.example.perfume.survey.domain;

import lombok.Getter;

@Getter
public enum SurveyType {

    GENDERLESS("남자 여자"),
    DEFAULT("캐쥬얼 포멀"),
    FOUR_SEASON("봄 여름 가을 겨울");

    private final String value;

    SurveyType(String value) {
        this.value = value;
    }
}
