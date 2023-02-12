package com.example.perfume.survey.domain;

public enum Season {
    SPRING("봄"), SUMMER("여름"), AUTUMN("가을"), WINTER("겨울"), FOUR_SEASON("무관");

    private String season;
    Season(String season) {
        this.season=season;
    }

    public String getSeason(){
        return season;
    }
}
