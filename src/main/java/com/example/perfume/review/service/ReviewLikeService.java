package com.example.perfume.review.service;

import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.dto.like.ReviewLikeResponse;
import com.example.perfume.review.exception.ReviewPostNotFoundException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.repository.ReviewLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    private final ReviewBoardRepository reviewBoardRepository;


    public ReviewLikeService(ReviewLikeRepository reviewLikeRepository, ReviewBoardRepository reviewBoardRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
        this.reviewBoardRepository = reviewBoardRepository;
    }

    @Transactional
    public void likePost(ReviewLikeRequest reviewLikeRequest) {
        ReviewLike reviewLike = reviewLikeRequest.toEntity();

        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(
                        reviewLike.getLikedPost().getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        reviewLike.updateStatus(reviewLike.getLikeStatus());
        if (!isAlreadyPushLikeOrUnlike(reviewLikeRequest)) {
            reviewLikeRepository.save(reviewLike);
        }
        perfumeReviewBoard.likePost(reviewLike);
    }

    @Transactional
    public void unlikePost(ReviewLikeRequest reviewLikeRequest) {
        ReviewLike reviewLike = reviewLikeRequest.toEntity();

        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(
                        reviewLike.getLikedPost().getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        reviewLike.updateStatus(reviewLike.getLikeStatus());
        if (!isAlreadyPushLikeOrUnlike(reviewLikeRequest)) {
            reviewLikeRepository.save(reviewLike);
        }
        perfumeReviewBoard.unlikePost(reviewLike);
    }

    public boolean isAlreadyPushLikeOrUnlike(ReviewLikeRequest reviewLikeRequest) {
        Long memberId = reviewLikeRequest.getMemberId();
        Long postId = reviewLikeRequest.getPostId();
        return reviewLikeRepository.existsByMemberAndReviewId(memberId, postId);
    }

    public ReviewLikeResponse showLikeCount(Long boardId) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(boardId)
                .orElseThrow(ReviewPostNotFoundException::new);

        return new ReviewLikeResponse(perfumeReviewBoard.getLikeCount(), perfumeReviewBoard.getUnlikeCount());
    }
}
