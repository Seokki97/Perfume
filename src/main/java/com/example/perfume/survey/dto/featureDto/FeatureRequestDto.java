package com.example.perfume.survey.dto.featureDto;

import com.example.perfume.survey.domain.Feature;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Table;
import java.util.List;

@Getter
@Table(name = "feature")
public class FeatureRequestDto {

    private Long id;

    private String answerOfSurvey;

    public Long perfumeId;


    public FeatureRequestDto() {

    }

    @Builder
    public FeatureRequestDto(Long id, String answerOfSurvey) {
        this.id = id;
        this.answerOfSurvey = answerOfSurvey;
    }

    public FeatureRequestDto(String answerOfSurvey) {
        this.answerOfSurvey = answerOfSurvey;
    }

    public Feature toEntity() {
        return Feature.builder()
                .id(id)
                .answerOfSurvey(answerOfSurvey)
                .build();
    }
}
