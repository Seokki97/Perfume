package com.example.perfume.member.repository;

import com.example.perfume.member.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
