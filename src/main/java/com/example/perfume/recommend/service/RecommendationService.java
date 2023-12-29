package com.example.perfume.recommend.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.RecommendRequestDto;
import com.example.perfume.recommend.dto.RecommendResponseDto;
import com.example.perfume.recommend.repository.RecommendRepository;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.service.SurveyService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecommendationService {

    private final RecommendRepository recommendRepository;

    private final SurveyService surveyService;

    private final MemberService memberService;

    public RecommendationService(RecommendRepository recommendRepository, SurveyService surveyService,
                                 MemberService memberService) {
        this.recommendRepository = recommendRepository;
        this.surveyService = surveyService;
        this.memberService = memberService;
    }

    public Recommendation recommendByOtherGuest(RecommendRequestDto recommendRequestDto) {
        long recommendedMemberId = recommendRequestDto.getRecommendedMemberId();
        Member recommendedMember = memberService.findMemberById(recommendedMemberId);

        String scentAnswer = recommendRequestDto.getScentAnswer();

        Recommendation recommendation = Recommendation.builder()
                .member(recommendedMember)
                .perfume(findPerfumeBySurvey(recommendRequestDto))
                .recommender(recommendRequestDto.getRecommender())
                .comment(recommendRequestDto.getComment())
                .scentAnswer(scentAnswer)
                .build();
        recommendRepository.save(recommendation);
        return recommendation;
    }

    private Perfume findPerfumeBySurvey(RecommendRequestDto recommendRequestDto) {
        SurveyRequestDto surveyRequestDto = recommendRequestDto.getSurveyAnswers();
        surveyRequestDto.addQueryParameter();
        return surveyService.showRecommendedPerfume(surveyRequestDto);
    }

    @Transactional
    public RecommendResponseDto showRecommendedPerfume(Long id) {
        Member member = memberService.findMemberById(id);
        List<Recommendation> recommendedList = recommendRepository.findByMemberId(member.getMemberId());

        return RecommendResponseDto.builder()
                .memberId(member.getMemberId())
                .recommendationList(recommendedList)
                .build();
    }

    @Transactional
    public void deleteRecommendedData() {
        recommendRepository.deleteAll();
    }
}

