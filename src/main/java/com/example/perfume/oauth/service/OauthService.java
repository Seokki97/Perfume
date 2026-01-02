package com.example.perfume.oauth.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class OauthService {
    private final MemberService memberService;

    public OauthService(MemberService memberService) {
        this.memberService = memberService;
    }

    public Member saveUserProfile(MemberRequestDto memberRequestDto) {
        return memberService.registerIfAbsent(memberRequestDto);
    }
}
