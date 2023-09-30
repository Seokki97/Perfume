package com.example.perfume.perfume.dto;


import com.example.perfume.perfume.domain.Perfume;
import lombok.*;

@Getter
public class PerfumeRequestDto {

    private Long perfumeId;
    private String perfumeName;
    private String brandName;
    private String perfumeFeature;
    private String perfumeImageUrl;

    public PerfumeRequestDto() {

    }

    @Builder
    public PerfumeRequestDto(Long perfumeId, String perfumeName, String brandName, String perfumeFeature, String perfumeImageUrl) {
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.perfumeImageUrl = perfumeImageUrl;
    }

    public Perfume toEntity() {
        return Perfume.builder()
                .perfumeId(perfumeId)
                .perfumeName(perfumeName)
                .brandName(brandName)
                .perfumeFeature(perfumeFeature)
                .perfumeImageUrl(perfumeImageUrl)
                .build();
    }
}
