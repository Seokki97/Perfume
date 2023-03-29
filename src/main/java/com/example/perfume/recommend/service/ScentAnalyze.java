package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.AnalyzeResponse;
import com.example.perfume.recommend.dto.ScentAnalyzeResponse;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScentAnalyze implements Analyze {
    private final RecommendRepository recommendRepository;

    private final AnalyzeUtil analyzeUtil;

    public ScentAnalyze(RecommendRepository recommendRepository, AnalyzeUtil analyzeUtil) {
        this.recommendRepository = recommendRepository;
        this.analyzeUtil = analyzeUtil;
    }

    @Override
    public List<String> extractRecommendedElement(Long memberId) {
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(memberId);
        List<String> perfumeList = new ArrayList<>();
        for (Recommendation recommendation : recommendationList) {
            perfumeList.add(recommendation.getScentAnswer());
        }
        return perfumeList;
    }

    public ScentAnalyzeResponse filterMostRecommendedScent(Long memberId) {
        List<String> scentList = extractRecommendedElement(memberId);

        AnalyzeResponse analyzeResponse = analyzeUtil.countPerfumeList(scentList);

        return ScentAnalyzeResponse.builder()
                .scent(analyzeResponse.getElementName())
                .count(analyzeResponse.getCount())
                .build();
    }
}
