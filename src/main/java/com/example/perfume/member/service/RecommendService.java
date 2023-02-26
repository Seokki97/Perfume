package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommend;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.dto.recommendDto.RecommendResponseDto;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.RecommendRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.service.SurveyService;
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

    public void recommendByOtherGuest(MemberRequestDto memberRequestDto,SurveyResponseDto surveyResponseDto){
        List<Survey> recommendedPerfume = surveyService.compareData(surveyResponseDto); //취향기반 향수찾기를 한 결과
        Member member = memberRepository.findByMemberId(memberRequestDto.getMemberId()).orElseThrow(UserNotFoundException::new);
        Recommend recommend = Recommend.builder()
                .member(member)
                .survey(recommendedPerfume)
                .build();
        recommendRepository.save(recommend);
    } //향수를 추천받아서 그 리스트를 멤버와 추천목록 같이 저장함
}
