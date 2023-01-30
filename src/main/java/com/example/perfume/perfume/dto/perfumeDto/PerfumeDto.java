package com.example.perfume.perfume.dto.perfumeDto;


import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.domain.Perfume;
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
//    private Feature feature;

    @Builder
    public PerfumeDto(Long id, String perfumeName, String perfumeFeature, String brandName/*, Feature feature*/) {
        this.id = id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        //  this.feature = feature;
    }

    public Perfume toEntity(/*Feature feature*/) {
        return Perfume.builder()
                .id(id)
                .perfumeName(perfumeName)
                .brandName(brandName)
                .perfumeFeature(perfumeFeature)
                // .feature(feature)
                .build();
    }
}
