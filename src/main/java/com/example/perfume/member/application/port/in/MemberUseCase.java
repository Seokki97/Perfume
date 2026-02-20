package com.example.perfume.member.application.port.in;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;

public interface MemberUseCase {
    Member findMemberById(Long id);

    Member findMemberByEmail(String email);

    Member findByMemberPk(Long memberId);

    boolean isAlreadyExistMember(MemberRequestDto memberRequestDto);

    Member saveMemberProfile(MemberRequestDto memberRequestDto);

    Member findByKakaoId(Long kakaoId);

    Member registerIfAbsent(MemberRequestDto memberRequestDto);

    void deleteMemberId(SecessionRequest secessionRequest);

    boolean isMemberLogout(String accessToken);

    void deleteAllMember();

    void deleteMember(Long memberId);
}
