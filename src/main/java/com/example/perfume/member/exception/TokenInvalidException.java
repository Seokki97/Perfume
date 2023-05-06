package com.example.perfume.member.exception;

import com.example.perfume.advice.BadRequestException;

public class TokenInvalidException extends BadRequestException {

    private static final String message = "토큰이 유효하지 않습니다";
    public TokenInvalidException() {
        super(message);
    }
}
