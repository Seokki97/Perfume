package com.example.perfume.wishlist.exception;

import com.example.perfume.advice.BadRequestException;

public class RankingCannotMakeException extends BadRequestException {

    private static final String message = "랭킹 산정할 데이터가 없습니다";

    public RankingCannotMakeException() {
        super(message);
    }
}
