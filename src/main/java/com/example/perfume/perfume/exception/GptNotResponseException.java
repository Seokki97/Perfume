package com.example.perfume.perfume.exception;

import com.example.perfume.advice.BadRequestException;

public class GptNotResponseException extends BadRequestException {
    private static final String message = "GPT가 답변을 생성하지 못했습니다.";

    public GptNotResponseException() {
        super(message);
    }
}
