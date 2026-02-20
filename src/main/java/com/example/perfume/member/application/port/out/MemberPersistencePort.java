package com.example.perfume.member.application.port.out;

import com.example.perfume.member.domain.Member;

import java.util.Optional;

public interface MemberPersistencePort {
    Optional<Member> findMemberById(Long memberId);

    Optional<Member> findMemberByEmail(String email);

    Optional<Member> findMemberByKakaoId(Long kakaoId);

    boolean existsByKakaoId(Long kakaoId);

    Member saveMember(Member member);

    long deleteMemberById(Long memberId);

    long deleteTokenByRefreshToken(String refreshToken);

    long deleteTokenByMemberId(Long memberId);

    boolean existsByAccessTokenInBlacklist(String accessToken);

    void deleteAllMembers();

    void deleteAllTokens();
}
