package com.example.perfume.wishlist.dto;

import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class WishRankingResponse {

    private Map<String, Long> rankingMap;

    @Builder
    public WishRankingResponse(Map<String, Long> rankingMap) {
        this.rankingMap = rankingMap;
    }

}
