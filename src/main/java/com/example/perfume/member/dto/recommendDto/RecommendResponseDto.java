package com.example.perfume.member.dto.recommendDto;


import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RecommendResponseDto {

    private Long id;

    private String recommender;

    private String comment;

    private List<Recommendation> recommendationList;

    public RecommendResponseDto() {
    }

    @Builder
    public RecommendResponseDto(Long id, String recommender,String comment, List<Recommendation> recommendationList) {
        this.id = id;
        this.recommender = recommender;
        this.comment = comment;
        this.recommendationList = recommendationList;
    }

}
