package com.example.perfume.member.exception;

import com.example.perfume.advice.BadRequestException;

public class TokenInvalidException extends BadRequestException {

    private static final String message = "해당 토큰을 가진 사용자가 없습니다";
    public TokenInvalidException() {
        super(message);
    }
}
