package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.BlacklistRepository;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final BlacklistRepository blacklistRepository;

    public MemberService(MemberRepository memberRepository,
                         TokenRepository tokenRepository,
                         BlacklistRepository blacklistRepository) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
        this.blacklistRepository = blacklistRepository;
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Member findMemberByEmail(String token) {
        return memberRepository.findByEmail(token)
                .orElseThrow(UserNotFoundException::new);
    }

    public Member findByMemberPk(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);
    }

    public boolean isAlreadyExistMember(MemberRequestDto memberRequestDto) {
        return memberRepository.existsByMemberId(memberRequestDto.getKakaoId());
    }

    public void saveMemberProfile(Member member) {
        memberRepository.save(member);
    }

    public void deleteMemberId(SecessionRequest secessionRequest) {
        memberRepository.deleteByMemberId(secessionRequest.getMemberId()).orElseThrow(UserNotFoundException::new);
        tokenRepository.deleteByRefreshToken(secessionRequest.getRefreshToken())
                .orElseThrow(() -> new TokenInvalidException("Refresh Token이 존재하지 않습니다."));
    }

    public boolean isMemberLogout(String accessToken) {
        return !blacklistRepository.existsByAccessToken(accessToken);
    }

    @Transactional
    public void deleteAllMember() {
        memberRepository.deleteAll();
        tokenRepository.deleteAll();
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteByMemberId(memberId);
        tokenRepository.deleteByMemberId(memberId);
    }
}
