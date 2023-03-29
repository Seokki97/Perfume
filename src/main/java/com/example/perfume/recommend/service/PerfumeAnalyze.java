package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.AnalyzeResponse;
import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfumeAnalyze implements Analyze {
    private final RecommendRepository recommendRepository;

    private final AnalyzeUtil analyzeUtil;

    public PerfumeAnalyze(RecommendRepository recommendRepository, AnalyzeUtil analyzeUtil) {
        this.analyzeUtil = analyzeUtil;
        this.recommendRepository = recommendRepository;
    }

    @Override
    public List<String> extractRecommendedElement(Long memberId) {
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(memberId);
        List<String> perfumeList = new ArrayList<>();
        for (Recommendation recommendation : recommendationList) {
            perfumeList.add(recommendation.getPerfume().getPerfumeName());
        }
        return perfumeList;
    }

    public PerfumeAnalyzeResponse filterMostRecommendedPerfumeName(Long memberId) {
        List<String> perfumeNameList = extractRecommendedElement(memberId);

        AnalyzeResponse analyzeResponse = analyzeUtil.countPerfumeList(perfumeNameList);
        return PerfumeAnalyzeResponse.builder()
                .perfumeName(analyzeResponse.getElementName())
                .countNumber(analyzeResponse.getCount())
                .build();
    }

}
