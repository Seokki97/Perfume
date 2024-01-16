package com.example.perfume.review.exception;

import com.example.perfume.advice.BadRequestException;

public class AlreadyPushLikeException extends BadRequestException {
    private static final String message = "이미 해당 게시글에 좋아요를 눌렀습니다.";

    public AlreadyPushLikeException() {
        super(message);
    }
}
