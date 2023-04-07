package com.example.perfume.recommend.service.analyze;

import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.dto.ScentAnalyzeResponse;
import com.example.perfume.recommend.service.PerfumeAnalyze;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyzeService {

    private final PerfumeAnalyze perfumeAnalyze;
    private final ScentAnalyze scentAnalyze;

    public AnalyzeService(PerfumeAnalyze perfumeAnalyze, ScentAnalyze scentAnalyze) {
        this.perfumeAnalyze = perfumeAnalyze;
        this.scentAnalyze = scentAnalyze;
    }

    public Map<String, Long> showAnalyzedData(Long memberId) {
        Map<String, Long> analyzedData = new HashMap<>();
        PerfumeAnalyzeResponse perfumeAnalyzeResponse = perfumeAnalyze.filterMostRecommendedPerfumeName(memberId);
        ScentAnalyzeResponse scentAnalyzeResponse = scentAnalyze.filterMostRecommendedScent(memberId);

        analyzedData.put(perfumeAnalyzeResponse.getPerfumeName(), perfumeAnalyzeResponse.getCountNumber());
        analyzedData.put(scentAnalyzeResponse.getScent(), scentAnalyzeResponse.getCount());

        return analyzedData;
    }
}
