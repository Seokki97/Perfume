package com.example.perfume.member.repository;

import com.example.perfume.member.domain.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommendation, Long> {

    @Query("SELECT r FROM recommend r JOIN FETCH r.perfume WHERE r.member.id = :memberId")
    List<Recommendation> findByMemberId(@Param("memberId") Long memberId);

    Optional<Recommendation> findById(Long memberId);

}
