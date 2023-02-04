package com.example.perfume.survey.dto.featureDto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Builder
@Getter
public class FeatureDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String answerOfSurvey;


    public Long perfumeId;


}
