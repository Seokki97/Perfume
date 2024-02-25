package com.example.perfume.review.exception;

import com.example.perfume.advice.BadRequestException;

public class LikedPostNotFoundException extends BadRequestException {

    private static final String message = "해당 게시글에 좋아요를 누르지 않았습니다.";

    public LikedPostNotFoundException() {
        super(message);
    }
}
