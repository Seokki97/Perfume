package com.example.perfume.review.repository;

import com.example.perfume.review.domain.like.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    boolean existsByMemberAndReviewId(Long memberId,Long reviewId);
}
