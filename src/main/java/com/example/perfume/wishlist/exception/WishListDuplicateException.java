package com.example.perfume.wishlist.exception;

import com.example.perfume.advice.BadRequestException;

public class WishListDuplicateException extends BadRequestException {
    private static final String message = "이미 위시리스트에 추가된 향수입니다.";

    public WishListDuplicateException() {
        super(message);
    }
}
