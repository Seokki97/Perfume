package com.example.perfume.perfume.exception;

import com.example.perfume.advice.BadRequestException;

public class BrandNotFoundException extends BadRequestException {
    private static final String message = "해당 브랜드의 향수가 없습니다.";

    public BrandNotFoundException() {
        super(message);
    }
}
