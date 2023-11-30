package com.example.perfume.member.exception;

import com.example.perfume.advice.BadRequestException;

public class TokenInvalidException extends BadRequestException {


    public TokenInvalidException(String message) {
        super(message);
    }
}
