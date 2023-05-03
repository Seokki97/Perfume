package com.example.perfume.wishlist.exception;

import com.example.perfume.advice.BadRequestException;

public class WishListNotFoundException extends BadRequestException {
    private static final String message = "즐겨찾기한 향수가 없습니다.";

    public WishListNotFoundException() {
        super(message);
    }
}
