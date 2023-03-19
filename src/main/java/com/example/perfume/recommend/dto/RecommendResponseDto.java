package com.example.perfume.recommend.dto;


import com.example.perfume.recommend.domain.Recommendation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RecommendResponseDto {

    private Long id;


    private List<Recommendation> recommendationList;

    public RecommendResponseDto() {
    }

    @Builder
    public RecommendResponseDto(Long id, List<Recommendation> recommendationList) {
        this.id = id;
        this.recommendationList = recommendationList;
    }

}
