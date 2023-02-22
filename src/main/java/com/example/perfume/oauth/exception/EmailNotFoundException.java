package com.example.perfume.oauth.exception;

import com.example.perfume.advice.BadRequestException;

public class EmailNotFoundException extends BadRequestException {
    private static final String message = "계정 이용 동의를 하지 않았습니다.";
    public EmailNotFoundException() {
        super(message);
    }
}
