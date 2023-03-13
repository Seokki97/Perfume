package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.exception.RecommendNotFoundException;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfumeAnalyze {
    private final RecommendRepository recommendRepository;

    public PerfumeAnalyze(RecommendRepository recommendRepository) {

        this.recommendRepository = recommendRepository;
    }
    private List<String> extractRecommendedPerfume(Long memberId) { //추천된 향수 리스트에서 향수 id를 추출해 List 생성
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(memberId);
        List<String> perfumeList = new ArrayList<>();
        for (Recommendation recommendation : recommendationList) {
            perfumeList.add(recommendation.getPerfume().getPerfumeName());
        }
        return perfumeList;
    }

    public PerfumeAnalyzeResponse filterMostRecommendedPerfumeName(Long memberId) {
        List<String> perfumeNameList = extractRecommendedPerfume(memberId);
        Long maxCount = 0L;
        String perfumeName = "";

        for (int i = 0; i < perfumeNameList.size(); i++) {
            Long count = countPerfume(perfumeNameList, i);
            if (count > maxCount) {
                perfumeName = perfumeNameList.get(i);
                maxCount = count;
            }
        }
        AnalyzeUtil.isCountingNumberExist(maxCount);
        return PerfumeAnalyzeResponse.builder()
                .perfumeName(perfumeName)
                .countNumber(maxCount).build();
    }

    private Long countPerfume(List<String> perfumeNameList, int i) {
        return perfumeNameList.stream().filter(x -> perfumeNameList.get(i).matches(x)).count();
    }


}
