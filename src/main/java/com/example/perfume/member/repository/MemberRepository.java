package com.example.perfume.member.repository;

import com.example.perfume.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByKakaoId(Long kakaoId);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByKakaoId(Long kakaoId);

    long deleteByMemberId(Long memberId);
}
