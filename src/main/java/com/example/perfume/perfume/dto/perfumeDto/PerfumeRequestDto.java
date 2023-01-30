package com.example.perfume.perfume.dto.perfumeDto;


import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.domain.Perfume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Table;


@RequiredArgsConstructor
@Table(name = "perfume")
@Data
public class PerfumeRequestDto {

    private Long id;
    private String perfumeName;
    private String brandName;
    private String perfumeFeature;

    private String perfumeImageUrl;

    @Builder
    public PerfumeRequestDto(Long id, String perfumeName, String brandName, String perfumeFeature, String perfumeImageUrl) {
        this.id = id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.perfumeImageUrl = perfumeImageUrl;
    }

    public Perfume toEntity(){
        return Perfume.builder()
                .id(id)
                .perfumeName(perfumeName)
                .brandName(brandName)
                .perfumeFeature(perfumeFeature)
                .perfumeImageUrl(perfumeImageUrl)
                .build();
    }
}
