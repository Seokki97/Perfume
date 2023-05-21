package com.example.perfume.recommend.dto.analyze;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingResponse {

    private PerfumeAnalyzeResponse perfumeAnalyzeObject;
    private ScentAnalyzeResponse scentAnalyzeObject;

    public RankingResponse() {
    }

    @Builder
    public RankingResponse(PerfumeAnalyzeResponse perfumeAnalyzeResponse, ScentAnalyzeResponse scentAnalyzeResponse) {
        this.perfumeAnalyzeObject = perfumeAnalyzeResponse;
        this.scentAnalyzeObject = scentAnalyzeResponse;
    }
}
