package com.example.perfume.perfume.dto.featureDto;

import com.example.perfume.perfume.domain.Feature;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;

@Getter
@Table(name = "feature")
public class FeatureResponseDto {

    private Long id;

    private String answerOfSurvey;

    public Long perfumeId;

    @Builder
    public FeatureResponseDto(Long id,String answerOfSurvey) {
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
