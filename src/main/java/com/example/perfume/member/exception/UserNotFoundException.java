package com.example.perfume.member.exception;

import com.example.perfume.advice.BadRequestException;

public class UserNotFoundException extends BadRequestException {
    private static final String message = "해당 유저를 찾을 수가 없습니다." ;

    public UserNotFoundException() {
        super(message);
    }
}
