package com.example.perfume.member.dto.recommendDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AnalyzeResponseDto {

    private String scent;
    private int count;


    @Builder
    public AnalyzeResponseDto(String scent, int count){
        this.scent = scent;
        this.count = count;
    }
}
