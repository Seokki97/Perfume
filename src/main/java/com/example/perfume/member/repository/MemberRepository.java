package com.example.perfume.member.repository;

import com.example.perfume.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Long, Member> {
}
