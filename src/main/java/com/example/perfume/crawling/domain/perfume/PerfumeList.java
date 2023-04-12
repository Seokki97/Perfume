package com.example.perfume.crawling.domain.perfume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PerfumeList {

    private List<String> perfumeName;
    private List<String> perfumeFeature;

    private List<String> perfumeBrand;

    private List<String> perfumeImageUrl;

    private List<String> perfumeStory;


    @Builder
    public PerfumeList(List<String> perfumeName, List<String> perfumeFeature, List<String> perfumeBrand, List<String> perfumeImageUrl, List<String> perfumeStory) {
        this.perfumeName = perfumeName;
        this.perfumeBrand = perfumeFeature;
        this.perfumeFeature = perfumeBrand;
        this.perfumeImageUrl = perfumeImageUrl;
        this.perfumeStory = perfumeStory;
    }


    public int getMaxSize() {
        return perfumeName.size();
    }

}
