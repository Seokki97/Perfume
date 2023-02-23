package com.example.perfume.member.repository;

import com.example.perfume.member.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByMemberId(Long memberId);
    Optional<Token> deleteByMemberId(Long memberId);
}
