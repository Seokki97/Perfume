package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.ScentAnalyzeResponse;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScentAnalyze implements Analyze {
    private final RecommendRepository recommendRepository;

    public ScentAnalyze(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }


    public ScentAnalyzeResponse filterMostRecommendedScent(Long memberId) {
        List<String> scentList = extractRecommendedElement(memberId);
        Long maxCount = 0L;
        String scentAnswer = "";
        int scentListSize = scentList.size();
        for (int i = 0; i < scentListSize; i++) {
            Long count = countElement(scentList, i);
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

    @Override
    public List<String> extractRecommendedElement(Long memberId) {
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(memberId);
        List<String> perfumeList = new ArrayList<>();
        for (Recommendation recommendation : recommendationList) {
            perfumeList.add(recommendation.getScentAnswer());
        }
        return perfumeList;
    }

    @Override
    public Long countElement(List<String> elementList, int i) {
        return elementList.stream().filter(x -> elementList.get(i).matches(x)).count();
    }
}
