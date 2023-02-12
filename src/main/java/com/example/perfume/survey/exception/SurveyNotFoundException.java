package com.example.perfume.survey.exception;

import com.example.perfume.advice.BadRequestException;

public class SurveyNotFoundException extends BadRequestException {

   private static final String message = "해당 설문 응답을 찾을 수 없습니다.";

    public SurveyNotFoundException() {
        super(message);
    }
}
