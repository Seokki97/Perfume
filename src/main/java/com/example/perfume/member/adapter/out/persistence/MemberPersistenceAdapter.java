package com.example.perfume.member.adapter.out.persistence;

import com.example.perfume.member.application.port.out.MemberPersistencePort;
import com.example.perfume.member.domain.Member;
import com.example.perfume.member.repository.BlacklistRepository;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberPersistenceAdapter implements MemberPersistencePort {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final BlacklistRepository blacklistRepository;

    public MemberPersistenceAdapter(MemberRepository memberRepository,
                                    TokenRepository tokenRepository,
                                    BlacklistRepository blacklistRepository) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
        this.blacklistRepository = blacklistRepository;
    }

    @Override
    public Optional<Member> findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> findMemberByKakaoId(Long kakaoId) {
        return memberRepository.findByKakaoId(kakaoId);
    }

    @Override
    public boolean existsByKakaoId(Long kakaoId) {
        return memberRepository.existsByKakaoId(kakaoId);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public long deleteMemberById(Long memberId) {
        return memberRepository.deleteByMemberId(memberId);
    }

    @Override
    public long deleteTokenByRefreshToken(String refreshToken) {
        return tokenRepository.deleteByRefreshToken(refreshToken);
    }

    @Override
    public long deleteTokenByMemberId(Long memberId) {
        return tokenRepository.deleteByMemberId(memberId);
    }

    @Override
    public boolean existsByAccessTokenInBlacklist(String accessToken) {
        return blacklistRepository.existsByAccessToken(accessToken);
    }

    @Override
    public void deleteAllMembers() {
        memberRepository.deleteAll();
    }

    @Override
    public void deleteAllTokens() {
        tokenRepository.deleteAll();
    }
}
