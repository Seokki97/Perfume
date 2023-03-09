package com.example.perfume.survey.exception;

import com.example.perfume.advice.BadRequestException;

public class MoodNotFoundException extends BadRequestException {
    private static final String message = "해당 무드를 찾을 수 없습니다.";

    public MoodNotFoundException() {
        super(message);
    }
}
