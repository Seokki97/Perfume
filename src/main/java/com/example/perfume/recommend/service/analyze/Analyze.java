package com.example.perfume.recommend.service.analyze;

import java.util.List;

public interface Analyze {
    List<String> extractRecommendedElement(Long memberId);
}
