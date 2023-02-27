package com.example.perfume.member.dto.recommendDto;


import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecommendResponseDto {

    private Long id;

    private Member member;

    public RecommendResponseDto() {
    }

    public RecommendResponseDto(Long id, Member member, List<Survey> surveyList) {
        this.id = id;
        this.member = member;
    }

    public Recommendation toEntity() {
        return Recommendation.builder()
                .id(id)
                .member(member)
                .build();
    }
}
