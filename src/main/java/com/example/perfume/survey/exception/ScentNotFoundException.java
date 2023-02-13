package com.example.perfume.survey.exception;

import com.example.perfume.advice.BadRequestException;

public class ScentNotFoundException extends BadRequestException {

    private static final String message = "해당 향을 찾을 수 없습니다.";

    public ScentNotFoundException() {
        super(message);
    }
}
