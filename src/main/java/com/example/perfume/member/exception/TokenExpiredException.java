package com.example.perfume.member.exception;

import com.example.perfume.advice.BadRequestException;

public class TokenExpiredException extends BadRequestException {

    private static final String message = "토큰이 만료되었습니다.";
    public TokenExpiredException() {
        super(message);
    }
}
