package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfumeAnalyze implements Analyze {
    private final RecommendRepository recommendRepository;

    public PerfumeAnalyze(RecommendRepository recommendRepository) {

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

    @Override
    public Long countElement(List<String> elementList, int i) {
        return elementList.stream().filter(x -> elementList.get(i).matches(x)).count();
    }

    public PerfumeAnalyzeResponse filterMostRecommendedPerfumeName(Long memberId) {
        List<String> perfumeNameList = extractRecommendedElement(memberId);
        Long maxCount = 0L;
        String perfumeName = "";
        int perfumeNameListSize = perfumeNameList.size();
        for (int i = 0; i < perfumeNameListSize; i++) {
            Long count = countElement(perfumeNameList, i);
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

}
