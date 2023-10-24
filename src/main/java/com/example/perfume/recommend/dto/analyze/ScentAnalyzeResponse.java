package com.example.perfume.recommend.dto.analyze;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScentAnalyzeResponse {

    private String scent;

    private Long count;

    @Builder
    public ScentAnalyzeResponse(String scent, Long count) {
        this.scent = scent;
        this.count = count;
    }
}
