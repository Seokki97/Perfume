package com.example.perfume.perfume.dto.perfumeDto;

import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PerfumeResponseDto {

    private Long id;
    private String perfumeName;
    private String brandName;
    private String perfumeFeature;
    private String perfumeImageUrl;


    public PerfumeResponseDto() {

    }

    @Builder
    public PerfumeResponseDto(Long id, String perfumeName, String brandName, String perfumeFeature, String perfumeImageUrl) {
        this.id = id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.perfumeImageUrl = perfumeImageUrl;
    }


    public Perfume toEntity() {
        return Perfume.builder()
                .id(id)
                .perfumeName(perfumeName)
                .brandName(brandName)
                .perfumeFeature(perfumeFeature)
                .perfumeImageUrl(perfumeImageUrl)
                .build();
    }
}
