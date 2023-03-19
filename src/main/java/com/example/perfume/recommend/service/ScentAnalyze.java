package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.ScentAnalyzeResponse;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScentAnalyze {
    private final RecommendRepository recommendRepository;

    public ScentAnalyze(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }

    private List<String> extractScentAnswer(Long memberId) {
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(memberId);
        List<String> perfumeList = new ArrayList<>();
        for (Recommendation recommendation : recommendationList) {
            perfumeList.add(recommendation.getScentAnswer());
        }
        return perfumeList;
    }

    public ScentAnalyzeResponse filterMostRecommendedScent(Long memberId) {
        List<String> scentList = extractScentAnswer(memberId);
        Long maxCount = 0L;
        String scentAnswer = "";

        for (int i = 0; i < scentList.size(); i++) {
            Long count = countScent(scentList, i);
            if (count > maxCount) {
                scentAnswer = scentList.get(i);
                maxCount = count;
            }
        }
        AnalyzeUtil.isCountingNumberExist(maxCount);
        return ScentAnalyzeResponse.builder()
                .scent(scentAnswer)
                .count(maxCount)
                .build();
    }

    private Long countScent(List<String> scentList, int i) {
        return scentList.stream().filter(x -> scentList.get(i).matches(x)).count();
    }
}
