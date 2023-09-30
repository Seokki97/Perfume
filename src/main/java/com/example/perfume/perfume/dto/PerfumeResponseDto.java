package com.example.perfume.perfume.dto;

import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PerfumeResponseDto {

    private Long perfumeId;
    private String perfumeName;
    private String brandName;
    private String perfumeFeature;
    private String perfumeImageUrl;

    private String maintenance;


    public PerfumeResponseDto() {

    }

    @Builder
    public PerfumeResponseDto(Long perfumeId, String perfumeName, String brandName, String perfumeFeature, String perfumeImageUrl, String maintenance) {
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.perfumeImageUrl = perfumeImageUrl;
        this.maintenance = maintenance;
    }


    public Perfume toEntity() {
        return Perfume.builder()
                .perfumeId(perfumeId)
                .perfumeName(perfumeName)
                .brandName(brandName)
                .perfumeFeature(perfumeFeature)
                .perfumeImageUrl(perfumeImageUrl)
                .maintenance(maintenance)
                .build();
    }
}
