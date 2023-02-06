package com.example.perfume.crawling.domain.perfume;

import com.example.perfume.crawling.service.PerfumeCSVFileLoading;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PerfumeList {

    private List<String> perfumeName;
    private List<String> perfumeFeature;


    private List<String> perfumeBrand;

    private List<String> perfumeImageUrl;


    @Builder
    public PerfumeList(List<String> perfumeName, List<String> perfumeFeature, List<String> perfumeBrand, List<String> perfumeImageUrl) {
        this.perfumeName = perfumeName;
        this.perfumeBrand = perfumeFeature;
        this.perfumeFeature = perfumeBrand;
        this.perfumeImageUrl = perfumeImageUrl;
    }


    public int getMaxSize(){
        return perfumeName.size();
    }

    public PerfumeList toEntity(){
        return PerfumeList.builder()
                .perfumeName(perfumeName)
                .perfumeFeature(perfumeFeature)
                .perfumeBrand(perfumeBrand)
                .perfumeImageUrl(perfumeImageUrl)
                .build();
    }
}
