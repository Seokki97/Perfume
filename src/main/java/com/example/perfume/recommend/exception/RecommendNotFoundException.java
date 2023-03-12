package com.example.perfume.recommend.exception;

import com.example.perfume.advice.BadRequestException;

public class RecommendNotFoundException extends BadRequestException {
    private static final String message = "추천받은 목록이 없습니다.";

    public RecommendNotFoundException() {
        super(message);
    }
}
