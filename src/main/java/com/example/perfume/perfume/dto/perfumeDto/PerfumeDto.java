package com.example.perfume.perfume.dto.perfumeDto;


import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Table;

@Builder
@Getter
@Table
public class PerfumeDto {
    //그렇기 때문에 클래스 변수를 final로 선언하고 객체의 생성은 빌더에 맡기는 것이 좋다.

    private Long id;
    private String perfumeName;
    private String brandName;
    private String perfumeFeature;

    private String perfumeImageUrl;
    @Builder
    public PerfumeDto(Long id, String perfumeName, String perfumeFeature, String brandName, String perfumeImageUrl) {
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
