package com.example.perfume.recommend.dto.analyze;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AnalyzeResponse {
    private String elementName;
    private Long count;

    public AnalyzeResponse() {
    }

    @Builder
    public AnalyzeResponse(String elementName, Long count) {
        this.elementName = elementName;
        this.count = count;
    }
}
