package com.example.perfume.review.repository;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    boolean existsByPostLikeMemberAndLikedPost(Member member, PerfumeReviewBoard perfumeReviewBoard);

    Optional<ReviewLike> findReviewLikeByPostLikeMemberAndLikedPost(Member member,
                                                                    PerfumeReviewBoard perfumeReviewBoard);

    Optional<ReviewLike> deleteReviewLikeByPostLikeMemberAndLikedPost(Member member,
                                                                      PerfumeReviewBoard perfumeReviewBoard);

    boolean existsReviewLikeByLikedPost(PerfumeReviewBoard perfumeReviewBoard);
}
