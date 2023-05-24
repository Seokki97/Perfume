package com.example.perfume.crawling.domain.survey;

public enum SurveyType {

    GENDER(0),SCENT(1),MOOD(2),SEASON(3),STYLE(4);

    private int value ;

    SurveyType(int value) {
        this.value = value;
    }

    public int selectTypeValue() {
        return value;
    }
}
