package com.example.perfume.recommend.dto.analyze;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PerfumeAnalyzeResponse {
    private Long count;
    private String perfumeName;

    @Builder
    public PerfumeAnalyzeResponse(Long count, String perfumeName) {
        this.count = count;
        this.perfumeName = perfumeName;
    }
}
