package com.example.perfume.member.application.service;

import com.example.perfume.member.application.port.in.MemberUseCase;
import com.example.perfume.member.application.port.out.MemberPersistencePort;
import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class MemberService implements MemberUseCase {

    private final MemberPersistencePort memberPersistencePort;

    public MemberService(MemberPersistencePort memberPersistencePort) {
        this.memberPersistencePort = memberPersistencePort;
    }

    @Override
    public Member findMemberById(Long id) {
        return memberPersistencePort.findMemberById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberPersistencePort.findMemberByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Member findByMemberPk(Long memberId) {
        return memberPersistencePort.findMemberById(memberId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean isAlreadyExistMember(MemberRequestDto memberRequestDto) {
        Long kakaoId = Objects.requireNonNull(memberRequestDto.getKakaoId(), "카카오 ID는 필수입니다.");
        return memberPersistencePort.existsByKakaoId(kakaoId);
    }

    @Override
    @Transactional
    public Member saveMemberProfile(MemberRequestDto memberRequestDto) {
        Objects.requireNonNull(memberRequestDto, "회원 정보는 필수입니다.");
        Member member = Member.create(
                memberRequestDto.getKakaoId(),
                memberRequestDto.getNickname(),
                memberRequestDto.getEmail(),
                memberRequestDto.getThumbnailImage()
        );
        return memberPersistencePort.saveMember(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findByKakaoId(Long kakaoId) {
        return memberPersistencePort.findMemberByKakaoId(kakaoId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public Member registerIfAbsent(MemberRequestDto memberRequestDto) {
        Long kakaoId = Objects.requireNonNull(memberRequestDto.getKakaoId(), "카카오 ID는 필수입니다.");
        return memberPersistencePort.findMemberByKakaoId(kakaoId)
                .orElseGet(() -> saveMemberProfile(memberRequestDto));
    }

    @Override
    @Transactional
    public void deleteMemberId(SecessionRequest secessionRequest) {
        long deletedMemberCount = memberPersistencePort.deleteMemberById(secessionRequest.getMemberId());
        if (deletedMemberCount == 0) {
            throw new UserNotFoundException();
        }
        long deletedTokenCount = memberPersistencePort.deleteTokenByRefreshToken(secessionRequest.getRefreshToken());
        if (deletedTokenCount == 0) {
            throw new TokenInvalidException("Refresh Token이 존재하지 않습니다.");
        }
    }

    @Override
    public boolean isMemberLogout(String accessToken) {
        return !memberPersistencePort.existsByAccessTokenInBlacklist(accessToken);
    }

    @Override
    @Transactional
    public void deleteAllMember() {
        memberPersistencePort.deleteAllMembers();
        memberPersistencePort.deleteAllTokens();
    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        long deletedMemberCount = memberPersistencePort.deleteMemberById(memberId);
        if (deletedMemberCount == 0) {
            throw new UserNotFoundException();
        }
        memberPersistencePort.deleteTokenByMemberId(memberId);
    }
}
