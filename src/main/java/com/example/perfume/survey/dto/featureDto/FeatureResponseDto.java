package com.example.perfume.survey.dto.featureDto;

import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeatureResponseDto {

    private String scentRecommend;
    private String moodRecommend;
    private String seasonRecommend;
    private Perfume perfume;

    private String maintenanceRecommend;

    public FeatureResponseDto() {

    }

    @Builder
    public FeatureResponseDto(String scentRecommend, String moodRecommend, String seasonRecommend,String maintenanceRecommend, Perfume perfume) {
        this.scentRecommend = scentRecommend;
        this.moodRecommend = moodRecommend;
        this.seasonRecommend = seasonRecommend;
        this.perfume = perfume;
        this.maintenanceRecommend = maintenanceRecommend;

    }
}
