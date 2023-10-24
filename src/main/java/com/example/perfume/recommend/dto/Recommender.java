package com.example.perfume.recommend.dto;

import lombok.Getter;

@Getter
public class Recommender {

    private String recommender;

    private Long recommendedMemberId;

    private String comment;

    public Recommender(String recommender, Long recommendedMemberId, String comment) {
        this.recommender = recommender;
        this.recommendedMemberId = recommendedMemberId;
        this.comment = comment;
    }
}
