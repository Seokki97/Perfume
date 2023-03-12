package com.example.perfume.member.service.recommend;

import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.member.dto.recommendDto.RecommendRequestDto;
import com.example.perfume.member.repository.RecommendRepository;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendRepository recommendRepository;

    private final SurveyService surveyService;

    private final MemberService memberService;
    private final PerfumeRepository perfumeRepository;


    public RecommendationService(RecommendRepository recommendRepository, SurveyService surveyService,
                                 MemberService memberService,
                                 PerfumeRepository perfumeRepository) {
        this.recommendRepository = recommendRepository;
        this.surveyService = surveyService;
        this.memberService = memberService;
        this.perfumeRepository = perfumeRepository;
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
        List<Perfume> surveyResultList = surveyService.compareData(createSurveyResponseDto(recommendRequestDto));
        Perfume perfume = perfumeRepository.findById(surveyResultList.get(0).getId()).orElseThrow(PerfumeNotFoundException::new);
        Recommendation recommendation = Recommendation.builder()
                .member(memberService.findMemberById(id))
                .perfume(perfume)
                .recommender(recommendRequestDto.getRecommender())
                .comment(recommendRequestDto.getComment())
                .build();
        recommendRepository.save(recommendation);
    }

    //추천받은 향수 조회 (토큰 인증 기능 추가해야함!!)
    @Transactional
    public List<Recommendation> showRecommendedPerfume(Long id) {
        Long memberId = memberService.findMemberById(id).getId();
        List<Recommendation> recommendationList = recommendRepository.findByMemberId(memberId);

        return recommendationList;
    }

    //추천받은 향수 세부 조회
}

