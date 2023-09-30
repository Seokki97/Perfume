package com.example.perfume.recommend.dto;


import com.example.perfume.recommend.domain.Recommendation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RecommendResponseDto {

    private Long memberId;

    private List<Recommendation> recommendationList;

    public RecommendResponseDto() {
    }

    @Builder
    public RecommendResponseDto(Long memberId, List<Recommendation> recommendationList) {
        this.memberId = memberId;
        this.recommendationList = recommendationList;
    }
}
