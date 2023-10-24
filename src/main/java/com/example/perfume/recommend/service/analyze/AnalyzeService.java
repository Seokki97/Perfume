package com.example.perfume.recommend.service.analyze;

import com.example.perfume.recommend.dto.analyze.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.dto.analyze.RankingResponse;
import com.example.perfume.recommend.dto.analyze.ScentAnalyzeResponse;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeService {

    private final PerfumeAnalyze perfumeAnalyze;
    private final ScentAnalyze scentAnalyze;

    public AnalyzeService(PerfumeAnalyze perfumeAnalyze, ScentAnalyze scentAnalyze) {
        this.perfumeAnalyze = perfumeAnalyze;
        this.scentAnalyze = scentAnalyze;
    }

    public RankingResponse responseAnalyzedData(Long memberId) {
        PerfumeAnalyzeResponse perfumeAnalyzeResponse = perfumeAnalyze.filterMostRecommendedPerfumeName(memberId);
        ScentAnalyzeResponse scentAnalyzeResponse = scentAnalyze.filterMostRecommendedScent(memberId);

        return RankingResponse.builder()
                .perfumeAnalyzeResponse(perfumeAnalyzeResponse)
                .scentAnalyzeResponse(scentAnalyzeResponse)
                .build();
    }
}
