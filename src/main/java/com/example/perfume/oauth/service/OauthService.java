package com.example.perfume.oauth.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.application.port.in.MemberUseCase;
import org.springframework.stereotype.Service;

@Service
public class OauthService {
    private final MemberUseCase memberService;

    public OauthService(MemberUseCase memberService) {
        this.memberService = memberService;
    }

    public Member saveUserProfile(MemberRequestDto memberRequestDto) {
        return memberService.registerIfAbsent(memberRequestDto);
    }
}
