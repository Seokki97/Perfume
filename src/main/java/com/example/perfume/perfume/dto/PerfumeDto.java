package com.example.perfume.perfume.dto;


import com.example.perfume.perfume.domain.Feature;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table
public class PerfumeDto {
    private Long id;
    private String perfumeName;
    private String brandName;
    private String perfumeFeature;
    private Feature feature;

    @Builder
    public PerfumeDto(Long id, String perfumeName, String brandName, String perfumeFeature, Feature feature) {
        this.id = id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.feature = feature;
    }
}
