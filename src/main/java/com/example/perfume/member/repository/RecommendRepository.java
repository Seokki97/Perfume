package com.example.perfume.member.repository;

import com.example.perfume.member.domain.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findByMemberId(Long memberId);

}
