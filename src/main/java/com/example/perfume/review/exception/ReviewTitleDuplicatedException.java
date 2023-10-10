package com.example.perfume.review.exception;

import com.example.perfume.advice.BadRequestException;

public class ReviewTitleDuplicatedException extends BadRequestException {
    private static final String message = "해당 제목의 게시물이 이미 존재합니다.";

    public ReviewTitleDuplicatedException() {
        super(message);
    }
}
