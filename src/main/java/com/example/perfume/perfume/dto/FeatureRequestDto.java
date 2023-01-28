package com.example.perfume.perfume.dto;

import com.example.perfume.perfume.domain.Feature;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "feature")
public class FeatureRequestDto {

    private Long id;
    private String firstFeature;
    private String secondFeature;
    private String thirdFeature;
    private String fourthFeature;

    @Builder
    public FeatureRequestDto(Long id, String firstFeature, String secondFeature, String thirdFeature, String fourthFeature) {
        this.id = id;
        this.firstFeature = firstFeature;
        this.secondFeature = secondFeature;
        this.thirdFeature = thirdFeature;
        this.fourthFeature = fourthFeature;
    }

    public Feature toEntity() {
        return Feature.builder()
                .id(id)
                .firstFeature(firstFeature)
                .secondFeature(secondFeature)
                .thirdFeature(thirdFeature)
                .fourthFeature(fourthFeature)
                .build();
    }
}
