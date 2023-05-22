package com.example.perfume.chatGpt.exception;

import com.example.perfume.advice.BadRequestException;

public class GptCannotMakeStoryException extends BadRequestException {
    private static final String message = "Chat GPT가 이야기를 생성하지 못했습니다.";

    public GptCannotMakeStoryException() {
        super(message);
    }
}
