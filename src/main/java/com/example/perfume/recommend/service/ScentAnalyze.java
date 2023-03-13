package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.dto.ScentAnalyzeResponse;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScentAnalyze {

    private final RecommendRepository recommendRepository;

    public ScentAnalyze(RecommendRepository recommendRepository){
        this.recommendRepository = recommendRepository;
    }
    /* 두번째 요구사항
        1. 향이 제일 많이 추천된 것을 찾는 기능
        2. 추천된 향수에서 가장 향이 많은것을 찾아 Response
     */
    public List<String> extractScentAnswer(Long memberId){
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
        PerfumeAnalyze.isCountingNumberExist(maxCount);
        return ScentAnalyzeResponse.builder()
                .scent(scentAnswer)
                .count(maxCount)
                .build();
    }

    public Long countScent(List<String> scentList, int i) {
        return scentList.stream().filter(x -> scentList.get(i).matches(x)).count();
    }
}
