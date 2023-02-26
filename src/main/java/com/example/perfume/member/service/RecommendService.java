package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommend;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.dto.recommendDto.RecommendRequestDto;
import com.example.perfume.member.dto.recommendDto.RecommendResponseDto;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.RecommendRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {

    private final RecommendRepository recommendRepository;

    private final LoginService loginService;

    private final SurveyService surveyService;
    private final MemberRepository memberRepository;

    public RecommendService(RecommendRepository recommendRepository, LoginService loginService, SurveyService surveyService,
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
        List<Survey> recommendedPerfume = surveyService.compareData(createSurveyResponseDto(recommendRequestDto)); //취향기반 향수찾기를 한 결과
        Survey survey = recommendedPerfume.get(0);
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Recommend recommend = Recommend.builder()
                .member(member)
                .survey(survey)
                .recommender(recommendRequestDto.getRecommender())
                .comment(recommendRequestDto.getComment())
                .build();
        recommendRepository.save(recommend);
    } //향수를 추천받아서 그 리스트를 멤버와 추천목록 같이 저장함

    //추천받은 향수 조회 (토큰 인증해야함)

    //추천받은 향수 세부 조회
}

