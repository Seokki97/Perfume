package com.example.perfume.oauth.exception;

import com.example.perfume.advice.BadRequestException;
import com.example.perfume.member.domain.Member;

public class MemberAlreadyExistException extends BadRequestException {
    private static final String message = "이미 해당 회원이 존재합니다.";

    public MemberAlreadyExistException(Member member) {
        super(message);
    }
}
