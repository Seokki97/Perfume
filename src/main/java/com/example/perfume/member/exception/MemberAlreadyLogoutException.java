package com.example.perfume.member.exception;

import com.example.perfume.advice.BadRequestException;

public class MemberAlreadyLogoutException extends BadRequestException {
    private static final String message = "이미 로그아웃한 상태입니다. 재로그인 해주세요";
    public MemberAlreadyLogoutException() {
        super(message);
    }
}
