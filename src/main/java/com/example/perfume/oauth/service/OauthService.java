package com.example.perfume.oauth.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class OauthService {
    private final MemberService memberService;

    private OauthService(MemberService memberService) {
        this.memberService = memberService;
    }

    public Member saveUserProfile(MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .email(memberRequestDto.getEmail())
                .kakaoId(memberRequestDto.getKakaoId())
                .nickname(memberRequestDto.getNickname())
                .thumbnailImage(memberRequestDto.getThumbnailImage())
                .build();

        if (!memberService.isAlreadyExistMember(memberRequestDto)) {
            memberService.saveMemberProfile(member);
        }
        return member;
    }
}