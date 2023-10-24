package com.example.perfume.wishlist.exception;

import com.example.perfume.advice.BadRequestException;

public class WishListTooMuchException extends BadRequestException {

    private static final String message = "향수 위시리스트는 최대 15개까지만 설정 가능합니다.";

    public WishListTooMuchException() {
        super(message);
    }
}
