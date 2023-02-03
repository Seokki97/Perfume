package com.example.perfume.perfume.dto.featureDto;

import com.example.perfume.perfume.domain.Feature;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Getter
@Table(name = "feature")
public class FeatureDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String answerOfSurvey;


    public Long perfumeId;


}
