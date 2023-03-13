package com.example.perfume.recommend.service;

import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.repository.RecommendRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerfumeAnalyze {

    private final RecommendRepository recommendRepository;

    private final PerfumeService perfumeService;

    public PerfumeAnalyze(RecommendRepository recommendRepository, PerfumeService perfumeService) {
        this.recommendRepository = recommendRepository;
        this.perfumeService = perfumeService;
    }

    /*  첫번째 요구사항
        1. 추천된 향수 리스트 들에서 향수 id를 추출해
        2. id값으로 Survey름 참조하여 scentAnswer을 추출해
        3. scentAnswer이 같은것끼리 합해서 가장 많이 나온것 두가지를 response해줘
     */
    public List<Long> extractRecommendedPerfumeId(Long memberId) { //추천된 향수 리스트에서 향수 id를 추출해 List 생성
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(memberId);
        List<Long> perfumeNum = new ArrayList<>();
        for (Recommendation recommendation : recommendationList) {
            perfumeNum.add(recommendation.getPerfume().getId());
        }System.out.println("perfumeNum Length:" + perfumeNum.size());
        return perfumeNum;
    }

    public List<String> extractPerfumeNameFromRecommendData(List<Long> perfumeNum){ //추천된 리스트에서 향수 이름들을 추출해 List생성
        List<String> perfumeNameList = new ArrayList<>();
        for(int i = 0 ; i<perfumeNum.size(); i++){
            perfumeNameList.add(perfumeService.findPerfumeById(perfumeNum.get(i)).getPerfumeName());
        }
        return perfumeNameList;
    }

    public PerfumeAnalyzeResponse filterMostRecommendedPerfumeName(Long memberId){
        List<String> perfumeNameList = extractPerfumeNameFromRecommendData(extractRecommendedPerfumeId(memberId));
        Long maxCount = 0l;
        String perfumeName = "";
        for(int i = 0; i<perfumeNameList.size() ; i++) {
            int finalI = i;
            Long count = perfumeNameList.stream().filter(x -> perfumeNameList.get(finalI).matches(x)).count();
            if(count>maxCount){
                perfumeName = perfumeNameList.get(i);
                maxCount = count;
            }
        }

        return PerfumeAnalyzeResponse.builder()
                .perfumeName(perfumeName)
                .countNumber(maxCount).build();
    }

}
