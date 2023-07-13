package com.example.perfume.review.service;


import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.repository.ReviewLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    public ReviewLikeService(ReviewLikeRepository reviewLikeRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
    }

    public void likePost(ReviewLikeRequest reviewLikeRequest) {
        ReviewLike reviewLike = reviewLikeRequest.toEntity();

        reviewLike.updateLike();
        if (!isAlreadyPushLikeOrUnlike(reviewLikeRequest)) {
            reviewLikeRepository.save(reviewLike);
        }
    }

    public void unlikePost(ReviewLikeRequest reviewLikeRequest) {
        ReviewLike reviewLike = reviewLikeRequest.toEntity();

        reviewLike.updateUnLike();

        if (!isAlreadyPushLikeOrUnlike(reviewLikeRequest)) {
            reviewLikeRepository.save(reviewLike);
        }
    }

    public boolean isAlreadyPushLikeOrUnlike(ReviewLikeRequest reviewLikeRequest) {
        Long memberId = reviewLikeRequest.getMember().getMemberId();
        Long postId = reviewLikeRequest.getPost().getBoardId();
        return reviewLikeRepository.existsByMemberAndReviewId(memberId, postId);
    }
}
