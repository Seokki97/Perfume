package com.example.perfume.perfume.dto;

import com.example.perfume.perfume.domain.Feature;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Data
@Table(name = "feature")
public class FeatureDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String firstFeature;
    private String secondFeature;
    private String thirdFeature;
    private String fourthFeature;


}
