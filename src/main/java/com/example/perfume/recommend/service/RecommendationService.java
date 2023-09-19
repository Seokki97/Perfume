package com.example.perfume.recommend.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.RecommendRequestDto;
import com.example.perfume.recommend.dto.RecommendResponseDto;
import com.example.perfume.recommend.dto.Recommender;
import com.example.perfume.recommend.repository.RecommendRepository;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendRepository recommendRepository;

    private final SurveyService surveyService;

    private final MemberService memberService;

    private final PerfumeService perfumeService;

    public RecommendationService(RecommendRepository recommendRepository, SurveyService surveyService,
                                 MemberService memberService,
                                 PerfumeService perfumeService) {
        this.recommendRepository = recommendRepository;
        this.surveyService = surveyService;
        this.memberService = memberService;
        this.perfumeService = perfumeService;
    }

    public Recommendation recommendByOtherGuest(RecommendRequestDto recommendRequestDto) {
        long recommendedMemberId = recommendRequestDto.getRecommendedMemberId();
        Member recommendedMember = memberService.findMemberById(recommendedMemberId);

        String scentAnswer = recommendRequestDto.getSurveyAnswers().getScentAnswer();

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
        List<Perfume> surveyResultList = surveyService.showPerfumeListBySurvey(surveyRequestDto);
        int randomNumber = RecommendUtils.createRandomPerfumeFromList(surveyResultList);
        return perfumeService.findPerfumeById(surveyResultList.get(randomNumber).getId());
    }

    @Transactional
    public RecommendResponseDto showRecommendedPerfume(Long id) {
        Long memberId = memberService.findMemberById(id).getId();

        return RecommendResponseDto.builder()
                .id(memberId)
                .recommendationList(recommendRepository.findByMemberId(memberId))
                .build();
    }

    @Transactional
    public void deleteRecommendedData() {
        recommendRepository.deleteAll();
    }
}

