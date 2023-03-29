package com.example.perfume.recommend.service;

import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;

import java.util.List;

public interface Analyze {
    List<String> extractRecommendedElement(Long memberId);

}
