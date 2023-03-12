package com.example.perfume.recommend.dto;

import lombok.Getter;

@Getter
public class RecommendRequestDto {

    private String genderAnswer;
    private String moodAnswer;
    private String scentAnswer;
    private String seasonAnswer;
    private String styleAnswer;

    private String recommender;
    private String comment;

    public RecommendRequestDto() {
    }

    public RecommendRequestDto(String recommender, String comment, String genderAnswer, String moodAnswer, String scentAnswer, String seasonAnswer, String styleAnswer) {
        this.recommender = recommender;
        this.comment = comment;
        this.genderAnswer = genderAnswer;
        this.moodAnswer = moodAnswer;
        this.scentAnswer = scentAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
    }

}
