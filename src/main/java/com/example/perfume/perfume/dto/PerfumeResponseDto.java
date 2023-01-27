package com.example.perfume.perfume.dto;

import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;

public class PerfumeResponseDto {

    private Long id;
    private String perfumeName;
    private String brandName;
    private String perfumeFeature;
    private Feature feature;

    @Builder
    public PerfumeResponseDto(Long id, String perfumeName, String brandName, String perfumeFeature, Feature feature) {
        this.id = id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.feature = feature;
    }

    public Perfume toEntity(Feature feature){
        return Perfume.builder()
                .id(id)
                .perfumeName(perfumeName)
                .brandName(brandName)
                .perfumeFeature(perfumeFeature)
                .feature(feature)
                .build();
    }
}
