package com.example.perfume.survey.dto.featureDto;

import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeatureResponseDto {

    private String scentRecommend;
    private String moodRecommend;
    private String seasonRecommend;

    private Perfume perfume;

    public FeatureResponseDto() {

    }


}
