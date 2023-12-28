package com.example.perfume.recommend.repository;

import com.example.perfume.recommend.domain.Recommendation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<Recommendation, Long> {

    @Query("SELECT r FROM recommend r JOIN FETCH r.perfume WHERE r.member.memberId = :memberId")
    List<Recommendation> findByMemberId(@Param("memberId") Long memberId);
}
