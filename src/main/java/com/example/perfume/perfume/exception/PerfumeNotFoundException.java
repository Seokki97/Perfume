package com.example.perfume.perfume.exception;

import com.example.perfume.advice.BadRequestException;

public class PerfumeNotFoundException extends BadRequestException {

    private static final String message = "해당 향수를 찾을 수 없습니다.";

    public PerfumeNotFoundException() {
        super(message);
    }
}
