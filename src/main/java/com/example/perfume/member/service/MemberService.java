package com.example.perfume.member.service;

import com.example.perfume.member.application.port.in.MemberUseCase;
import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;

/**
 * @deprecated use {@link com.example.perfume.member.application.service.MemberService} through {@link MemberUseCase}.
 */
@Deprecated
public class MemberService {
    private final MemberUseCase memberUseCase;

    public MemberService(MemberUseCase memberUseCase) {
        this.memberUseCase = memberUseCase;
    }

    public Member findMemberById(Long id) {
        return memberUseCase.findMemberById(id);
    }

    public Member findMemberByEmail(String email) {
        return memberUseCase.findMemberByEmail(email);
    }

    public Member findByMemberPk(Long memberId) {
        return memberUseCase.findByMemberPk(memberId);
    }

    public boolean isAlreadyExistMember(MemberRequestDto memberRequestDto) {
        return memberUseCase.isAlreadyExistMember(memberRequestDto);
    }

    public Member saveMemberProfile(MemberRequestDto memberRequestDto) {
        return memberUseCase.saveMemberProfile(memberRequestDto);
    }

    public Member findByKakaoId(Long kakaoId) {
        return memberUseCase.findByKakaoId(kakaoId);
    }

    public Member registerIfAbsent(MemberRequestDto memberRequestDto) {
        return memberUseCase.registerIfAbsent(memberRequestDto);
    }

    public void deleteMemberId(SecessionRequest secessionRequest) {
        memberUseCase.deleteMemberId(secessionRequest);
    }

    public boolean isMemberLogout(String accessToken) {
        return memberUseCase.isMemberLogout(accessToken);
    }

    public void deleteAllMember() {
        memberUseCase.deleteAllMember();
    }

    public void deleteMember(Long memberId) {
        memberUseCase.deleteMember(memberId);
    }
}
