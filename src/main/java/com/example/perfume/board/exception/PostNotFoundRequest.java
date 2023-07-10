package com.example.perfume.board.exception;

import com.example.perfume.advice.BadRequestException;

public class PostNotFoundRequest extends BadRequestException {
    private final static String message = "해당 게시글을 찾을 수 없습니다.";

    public PostNotFoundRequest() {
        super(message);
    }
}
