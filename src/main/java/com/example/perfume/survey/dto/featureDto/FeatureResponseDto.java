package com.example.perfume.survey.dto.featureDto;

import com.example.perfume.survey.domain.Feature;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Table;

@Getter
@Table(name = "feature")
public class FeatureResponseDto {

    private Long id;

    private String answerOfSurvey;

    public Long perfumeId;

    @Builder
    public FeatureResponseDto(Long id, String answerOfSurvey) {
        this.id = id;
        this.answerOfSurvey = answerOfSurvey;
    }

    public Feature toEntity(Feature feature) {
        return Feature.builder()
                .id(id)
                .answerOfSurvey(answerOfSurvey)
                .build();
    }
}
