package com.example.perfume.survey.domain.post.exception;

import com.example.perfume.advice.BadRequestException;

public class PostNotFoundException extends BadRequestException {
    private static final String message = "게시물을 찾을 수가 없습니다.";

    public PostNotFoundException() {
        super(message);
    }
}
