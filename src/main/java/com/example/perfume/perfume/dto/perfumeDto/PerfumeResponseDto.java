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

    private String perfumeStory;

    public PerfumeResponseDto() {

    }

    @Builder
    public PerfumeResponseDto(Long id, String perfumeName, String brandName, String perfumeFeature, String perfumeImageUrl, String perfumeStory) {
        this.id = id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.perfumeImageUrl = perfumeImageUrl;
        this.perfumeStory = perfumeStory;
    }


    public Perfume toEntity() {
        return Perfume.builder()
                .id(id)
                .perfumeName(perfumeName)
                .brandName(brandName)
                .perfumeFeature(perfumeFeature)
                .perfumeImageUrl(perfumeImageUrl)
                .perfumeStory(perfumeStory)
                .build();
    }
}
