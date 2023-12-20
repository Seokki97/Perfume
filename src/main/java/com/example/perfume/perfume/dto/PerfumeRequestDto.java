package com.example.perfume.perfume.dto;


import com.example.perfume.perfume.domain.Perfume;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PerfumeRequestDto {

    @Schema(description = "향수 id")
    private Long perfumeId;
    @Schema(description = "향수 이름")
    private String perfumeName;
    @Schema(description = "브랜드 이름")
    private String brandName;
    @Schema(description = "향수 특징")
    private String perfumeFeature;
    @Schema(description = "향수이미지 url")
    private String perfumeImageUrl;

    public PerfumeRequestDto() {

    }

    @Builder
    public PerfumeRequestDto(Long perfumeId, String perfumeName, String brandName, String perfumeFeature,
                             String perfumeImageUrl) {
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
