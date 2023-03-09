package com.example.perfume.member.service.recommend;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.member.dto.recommendDto.AnalyzeResponseDto;
import com.example.perfume.member.repository.RecommendRepository;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendAnalyze {
    private static final int RECOMMENDED_NUMBER = 0;
    private final RecommendationService recommendationService;
    private final SurveyService surveyService;


    public RecommendAnalyze(RecommendationService recommendationService, SurveyService surveyService) {
        this.recommendationService = recommendationService;
        this.surveyService = surveyService;
    }

    //추천받은 향수들에서 시트러스인지 우디인지 > 그게 몇개인지 Count되는 구조

    public List<String> countRecommendedData(Long memberId, Survey survey) {
        List<Recommendation> recommendedData = recommendationService.showRecommendedPerfume(memberId); // -> 추천된 향수 리스트들이 나옴
        List<String> scentValue = new ArrayList<>();

        Long perfumeId = recommendedData.get(RECOMMENDED_NUMBER).getPerfume().get(RECOMMENDED_NUMBER).getId();
        scentValue.add(surveyService.findSurveyById(perfumeId).getScentAnswer());

        return scentValue;

    }

}
