package com.example.perfume.survey.exception;

import com.example.perfume.advice.BadRequestException;

public class SeasonNotFoundException extends BadRequestException {

    private static final String message = "해당 계절을 찾을 수 없습니다.";

    public SeasonNotFoundException() {
        super(message);
    }
}
