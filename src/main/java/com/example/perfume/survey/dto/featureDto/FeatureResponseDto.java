package com.example.perfume.survey.dto.featureDto;

import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeatureResponseDto {

    private String scentFeature;
    private String moodFeature;
    private String seasonFeature;
    private Perfume perfume;

    private String maintenanceFeature;

    public FeatureResponseDto() {

    }

    @Builder
    public FeatureResponseDto(String scentFeature, String moodFeature, String seasonFeature, String maintenanceFeature,
                              Perfume perfume) {
        this.scentFeature = scentFeature;
        this.moodFeature = moodFeature;
        this.seasonFeature = seasonFeature;
        this.perfume = perfume;
        this.maintenanceFeature = maintenanceFeature;

    }
}
