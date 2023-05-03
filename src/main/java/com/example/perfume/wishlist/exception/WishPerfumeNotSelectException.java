package com.example.perfume.wishlist.exception;

import com.example.perfume.advice.BadRequestException;

public class WishPerfumeNotSelectException extends BadRequestException {

    private static final String message = "즐겨찾기할 향수를 선택하지 않았습니다.";

    public WishPerfumeNotSelectException() {
        super(message);
    }
}
