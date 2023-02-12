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
    public FeatureResponseDto(){

    }

    @Builder
    public FeatureResponseDto(Perfume perfume, String scentRecommend, String moodRecommend, String seasonRecommend) {
        this.perfume = perfume;
        this.scentRecommend = scentRecommend;
        this.moodRecommend = moodRecommend;
        this.seasonRecommend = seasonRecommend;
    }

}
