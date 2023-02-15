package com.example.perfume.advice;

public class BadRequestException extends BusinessException {

    public BadRequestException(String message) {
        super(message);
    }
}
