package com.example.perfume.recommend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PerfumeAnalyzeResponse {
    private Long countNumber;
    private String perfumeName;

    @Builder
    public PerfumeAnalyzeResponse(Long countNumber, String perfumeName) {

        this.countNumber = countNumber;
        this.perfumeName = perfumeName;
    }

}
