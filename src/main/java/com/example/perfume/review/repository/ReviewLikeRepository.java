package com.example.perfume.review.repository;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    boolean existsByMemberAndLikedPost(Member member, PerfumeReviewBoard perfumeReviewBoard);
}
