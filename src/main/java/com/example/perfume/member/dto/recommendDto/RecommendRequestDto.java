package com.example.perfume.member.dto.recommendDto;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommend;
import com.example.perfume.survey.domain.Survey;
import lombok.Getter;

@Getter
public class RecommendRequestDto {

    private String recommender;
    private String comment;

    public RecommendRequestDto() {
    }

    public RecommendRequestDto(String recommender, String comment) {
        this.recommender = recommender;
        this.comment = comment;
    }

}
