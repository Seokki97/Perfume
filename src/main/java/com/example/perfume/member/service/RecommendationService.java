package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.member.dto.recommendDto.RecommendRequestDto;
import com.example.perfume.member.dto.recommendDto.RecommendResponseDto;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.RecommendRepository;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendationService {

    private static final int RECOMMEND_PERFUME_INDEX = 0;
    private final RecommendRepository recommendRepository;

    private final LoginService loginService;

    private final SurveyService surveyService;
    private final MemberRepository memberRepository;

    public RecommendationService(RecommendRepository recommendRepository, LoginService loginService, SurveyService surveyService,
                                 MemberRepository memberRepository) {
        this.recommendRepository = recommendRepository;
        this.loginService = loginService;
        this.surveyService = surveyService;
        this.memberRepository = memberRepository;
    }

    public SurveyResponseDto createSurveyResponseDto(RecommendRequestDto recommendRequestDto) {
        SurveyResponseDto surveyResponseDto = SurveyResponseDto.builder()
                .genderAnswer(recommendRequestDto.getGenderAnswer())
                .moodAnswer(recommendRequestDto.getMoodAnswer())
                .scentAnswer(recommendRequestDto.getScentAnswer())
                .seasonAnswer(recommendRequestDto.getSeasonAnswer())
                .styleAnswer(recommendRequestDto.getStyleAnswer())
                .build();
        return surveyResponseDto;
    }

    public void recommendByOtherGuest(Long id, RecommendRequestDto recommendRequestDto) {
        List<Perfume> survey = surveyService.compareData(createSurveyResponseDto(recommendRequestDto)); //취향기반 향수찾기를 한 결과
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Recommendation recommendation = Recommendation.builder()
                .member(member)
                .perfume(survey)
                .recommender(recommendRequestDto.getRecommender())
                .comment(recommendRequestDto.getComment())
                .build();
        recommendRepository.save(recommendation);
    }
    //추천받은 향수 조회 (토큰 인증 기능 추가해야함!!)
    @Transactional
    public List<Recommendation> showRecommendedPerfume() {
        Member member = memberRepository.findById(1l).orElseThrow(UserNotFoundException::new);
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(member.getId());

        return recommendationList;
    }
    //추천받은 향수 세부 조회
}

