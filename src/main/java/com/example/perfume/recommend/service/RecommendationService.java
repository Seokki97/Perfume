package com.example.perfume.recommend.service;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.RecommendRequestDto;
import com.example.perfume.recommend.dto.RecommendResponseDto;
import com.example.perfume.recommend.repository.RecommendRepository;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

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

    private SurveyRequestDto createSurveyResponseDto(RecommendRequestDto recommendRequestDto) {
        SurveyRequestDto surveyRequestDto = SurveyRequestDto.builder()
                .genderAnswer(recommendRequestDto.getGenderAnswer())
                .moodAnswer(recommendRequestDto.getMoodAnswer())
                .scentAnswer(recommendRequestDto.getScentAnswer())
                .seasonAnswer(recommendRequestDto.getSeasonAnswer())
                .styleAnswer(recommendRequestDto.getStyleAnswer())
                .build();
        return surveyRequestDto;
    }

    public Recommendation recommendByOtherGuest(Long id, RecommendRequestDto recommendRequestDto) {
        Recommendation recommendation = Recommendation.builder()
                .member(memberService.findMemberById(id))
                .perfume(findPerfumeBySurvey(recommendRequestDto))
                .recommender(recommendRequestDto.getRecommender())
                .comment(recommendRequestDto.getComment())
                .scentAnswer(recommendRequestDto.getScentAnswer())
                .build();
        recommendRepository.save(recommendation);
        return recommendation;
    }

    private Perfume findPerfumeBySurvey(RecommendRequestDto recommendRequestDto) {
        List<Perfume> surveyResultList = surveyService.showPerfumeListBySurvey(createSurveyResponseDto(recommendRequestDto));
        int randomNumber = createRandomPerfumeFromList(surveyResultList);
        return perfumeService.findPerfumeById(surveyResultList.get(randomNumber).getId());
    }

    private int createRandomPerfumeFromList(List<Perfume> surveyResultList) {
        Random random = new Random();
        return random.nextInt(surveyResultList.size());
    }

    @Transactional
    public RecommendResponseDto showRecommendedPerfume(Long id) {
        Long memberId = memberService.findMemberById(id).getId();

        RecommendResponseDto recommendResponseDto = RecommendResponseDto.builder()
                .id(memberId)
                .recommendationList(recommendRepository.findByMemberId(memberId))
                .build();
        return recommendResponseDto;
    }

    @Transactional
    public void deleteRecommendedData(){
        recommendRepository.deleteAll();
    }
}

