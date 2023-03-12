package com.example.perfume.member.service.recommend;

import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.member.dto.recommendDto.RecommendRequestDto;
import com.example.perfume.member.dto.recommendDto.RecommendResponseDto;
import com.example.perfume.member.exception.RecommendNotFoundException;
import com.example.perfume.member.repository.RecommendRepository;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
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

    public Recommendation recommendByOtherGuest(Long id, RecommendRequestDto recommendRequestDto) {
        Recommendation recommendation = Recommendation.builder()
                .member(memberService.findMemberById(id))
                .perfume(findPerfumeBySurvey(recommendRequestDto))
                .recommender(recommendRequestDto.getRecommender())
                .comment(recommendRequestDto.getComment())
                .build();
        recommendRepository.save(recommendation);
        return recommendation;
    }

    public Perfume findPerfumeBySurvey(RecommendRequestDto recommendRequestDto){
     List<Perfume> surveyResultList = surveyService.compareData(createSurveyResponseDto(recommendRequestDto));
     int randomNumber = createRandomPerfumeFromList(surveyResultList);
     return perfumeService.findPerfumeById(surveyResultList.get(randomNumber).getId());
    }

    public int createRandomPerfumeFromList(List<Perfume> surveyResultList){
        Random random = new Random();
        return random.nextInt(surveyResultList.size());
    }

    //추천받은 향수 조회 (토큰 인증 기능 추가해야함!!)
    @Transactional
    public RecommendResponseDto showRecommendedPerfume(Long id) {
        Long memberId = memberService.findMemberById(id).getId();

        RecommendResponseDto recommendResponseDto = RecommendResponseDto.builder()
                .id(memberId)
                .recommendationList(recommendRepository.findByMemberId(memberId))
                .recommender(getRecommender(id))
                .comment(getComment(id))
                .build();
        return recommendResponseDto;
    }

    public String getRecommender(Long id){
       return findRecommendData(id).getRecommender();
    }

    public String getComment(Long id){
        return findRecommendData(id).getComment();
    }
    public Recommendation findRecommendData(Long id){
        return recommendRepository.findById(id).orElseThrow(RecommendNotFoundException::new);
    }

    //추천받은 향수 세부 조회
}

