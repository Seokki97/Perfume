package com.example.perfume.oauth.exception;

import com.example.perfume.advice.BadRequestException;

public class MemberAlreadyExistException extends BadRequestException {
    private static final String message = "이미 해당 회원이 존재합니다.";

    public MemberAlreadyExistException() {
        super(message);
    }
}
