package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {


    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Member findMemberByEmail(String token) {
        return memberRepository.findByEmail(token)
                .orElseThrow(UserNotFoundException::new);
    }

    public Member findByMemberPk(Long memberId){
        return memberRepository.findByMemberId(memberId).orElseThrow(UserNotFoundException::new);
    }
}