package com.example.perfume.wishlist.exception;

import com.example.perfume.advice.BadRequestException;

public class RequestBodyNullPointException extends BadRequestException {

    private static final String message = "RequestBody 값이 잘못되었습니다.";

    public RequestBodyNullPointException() {
        super(message);
    }
}
