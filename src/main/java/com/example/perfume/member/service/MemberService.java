package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {


    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

    public MemberService(MemberRepository memberRepository,
                         TokenRepository tokenRepository) {
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Member findMemberByEmail(String token) {
        return memberRepository.findByEmail(token)
                .orElseThrow(UserNotFoundException::new);
    }

    public Member findByMemberPk(Long memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow(UserNotFoundException::new);
    }

    public boolean isAlreadyExistMember(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByMemberId(memberRequestDto.getMemberId())) {
            return true;
        }
        return false;
    }

    public void saveMemberProfile(Member member){
        memberRepository.save(member);
    }

    public void deleteMemberId(SecessionRequest secessionRequest){
        //회원 삭제, 토큰 삭제
        memberRepository.deleteByMemberId(secessionRequest.getMemberId()).orElseThrow(UserNotFoundException::new);
        tokenRepository.deleteByRefreshToken(secessionRequest.getRefreshToken()).orElseThrow(TokenInvalidException::new);
    }
}
