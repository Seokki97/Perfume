package com.example.perfume.member.dto.recommendDto;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommend;
import com.example.perfume.survey.domain.Survey;

public class RecommendRequestDto {

    private Long id;

    private Member member;

    private Survey survey;

    public RecommendRequestDto() {
    }

    public RecommendRequestDto(Long id, Member member, Survey survey){
        this.id = id;
        this.member = member;
        this.survey = survey;
    }

    public Recommend toEntity(){
        return Recommend.builder()
                .id(id)
                .member(member)
                .survey(survey)
                .build();
    }
}
