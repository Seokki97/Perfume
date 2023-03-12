package com.example.perfume.recommend.dto;


import com.example.perfume.recommend.domain.Recommendation;
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
