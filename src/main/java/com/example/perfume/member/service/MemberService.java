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

import java.util.Objects;

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

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public Member findByMemberPk(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);
    }

    public boolean isAlreadyExistMember(MemberRequestDto memberRequestDto) {
        Long kakaoId = Objects.requireNonNull(memberRequestDto.getKakaoId(), "카카오 ID는 필수입니다.");
        return memberRepository.existsByKakaoId(kakaoId);
    }

    @Transactional
    public Member saveMemberProfile(MemberRequestDto memberRequestDto) {
        Objects.requireNonNull(memberRequestDto, "회원 정보는 필수입니다.");
        Member member = memberRequestDto.toEntity();
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findByKakaoId(Long kakaoId) {
        return memberRepository.findByKakaoId(kakaoId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public Member registerIfAbsent(MemberRequestDto memberRequestDto) {
        Long kakaoId = Objects.requireNonNull(memberRequestDto.getKakaoId(), "카카오 ID는 필수입니다.");
        return memberRepository.findByKakaoId(kakaoId)
                .orElseGet(() -> saveMemberProfile(memberRequestDto));
    }

    @Transactional
    public void deleteMemberId(SecessionRequest secessionRequest) {
        long deletedMemberCount = memberRepository.deleteByMemberId(secessionRequest.getMemberId());
        if (deletedMemberCount == 0) {
            throw new UserNotFoundException();
        }
        long deletedTokenCount = tokenRepository.deleteByRefreshToken(secessionRequest.getRefreshToken());
        if (deletedTokenCount == 0) {
            throw new TokenInvalidException("Refresh Token이 존재하지 않습니다.");
        }
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
        long deletedMemberCount = memberRepository.deleteByMemberId(memberId);
        if (deletedMemberCount == 0) {
            throw new UserNotFoundException();
        }
        tokenRepository.deleteByMemberId(memberId);
    }
}
