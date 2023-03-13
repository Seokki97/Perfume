package com.example.perfume.recommend.service;

import com.example.perfume.recommend.exception.RecommendNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeUtil {

    public static void isCountingNumberExist(Long maxCount) {
        if (maxCount < 1) {
            throw new RecommendNotFoundException();
        }
    }
}
