package com.example.perfume.member.repository;

import com.example.perfume.member.domain.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {

    boolean existsByAccessToken(String accessToken);
}
