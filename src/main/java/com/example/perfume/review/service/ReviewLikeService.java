package com.example.perfume.review.service;

import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.exception.ReviewPostNotFoundException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.repository.ReviewLikeRepository;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(reviewLike.getLikedPost().getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        reviewLike.updateLike();
        if (!isAlreadyPushLikeOrUnlike(reviewLikeRequest)) {
            reviewLikeRepository.save(reviewLike);
        }
        perfumeReviewBoard.likePost(reviewLike);
    }

    @Transactional
    public void unlikePost(ReviewLikeRequest reviewLikeRequest) {
        ReviewLike reviewLike = reviewLikeRequest.toEntity();

        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(reviewLike.getLikedPost().getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        reviewLike.updateUnLike();
        if (!isAlreadyPushLikeOrUnlike(reviewLikeRequest)) {
            reviewLikeRepository.save(reviewLike);
        }
        perfumeReviewBoard.unlikePost(reviewLike);
    }

    public boolean isAlreadyPushLikeOrUnlike(ReviewLikeRequest reviewLikeRequest) {
        Long memberId = reviewLikeRequest.getMember().getMemberId();
        Long postId = reviewLikeRequest.getPost().getBoardId();
        return reviewLikeRepository.existsByMemberAndReviewId(memberId, postId);
    }

    //라이크 많은 수 정렬
    public List<PerfumeReviewBoard> sortByMostLikeReviews(String content) {
        List<PerfumeReviewBoard> selectedPerfume = reviewBoardRepository
                .findByTitleContainingOrContentContaining(content, content);

        return selectedPerfume.stream()
                .sorted(Comparator.comparing(PerfumeReviewBoard::getLikeCount).reversed())
                .collect(Collectors.toList());
    }

    //Unlike 많은 수 정렬
    public List<PerfumeReviewBoard> sortByMostUnlikeReviews(String content) {
        List<PerfumeReviewBoard> selectedPerfume = reviewBoardRepository
                .findByTitleContainingOrContentContaining(content, content);

        return selectedPerfume.stream()
                .sorted(Comparator.comparing(PerfumeReviewBoard::getUnlikeCount).reversed())
                .collect(Collectors.toList());
    }
}
