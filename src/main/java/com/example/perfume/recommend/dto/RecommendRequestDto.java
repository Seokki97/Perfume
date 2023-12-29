package com.example.perfume.recommend.dto;

import com.example.perfume.survey.domain.Question;
import lombok.Getter;

@Getter
public class RecommendRequestDto {

    private Question question;

    private String recommender;

    private Long recommendedMemberId;

    private String comment;

    public RecommendRequestDto() {
    }

    public RecommendRequestDto(Question question, Long recommendedMemberId, String comment,
                               String recommender) {
        this.question = question;
        this.recommender = recommender;
        this.recommendedMemberId = recommendedMemberId;
        this.comment = comment;
    }

    public String getScentAnswer() {
        return question.getScentAnswer();
    }
}
