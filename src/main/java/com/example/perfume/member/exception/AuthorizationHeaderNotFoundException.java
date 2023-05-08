package com.example.perfume.member.exception;

import com.example.perfume.advice.BadRequestException;

public class AuthorizationHeaderNotFoundException extends BadRequestException {

    private static final String message = "Authorization 헤더를 찾을 수 없습니다.";

    public AuthorizationHeaderNotFoundException() {
        super(message);
    }
}
