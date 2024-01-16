package com.example.perfume.review.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.PostLike;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.LikeCount;
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
    private final MemberService memberService;
    private final PerfumeRepository perfumeRepository;


    public ReviewLikeService(ReviewLikeRepository reviewLikeRepository, ReviewBoardRepository reviewBoardRepository,
                             MemberService memberService,
                             PerfumeRepository perfumeRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
        this.reviewBoardRepository = reviewBoardRepository;
        this.memberService = memberService;
        this.perfumeRepository = perfumeRepository;
    }

    @Transactional
    public void likePost(ReviewLikeRequest reviewLikeRequest) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(reviewLikeRequest.getPostId())
                .orElseThrow(ReviewPostNotFoundException::new);
        Member member = perfumeReviewBoard.getWriter();

        ReviewLike reviewLike = ReviewLike.builder()
                .postLike(new PostLike(member, LikeStatus.LIKE))
                .likedPost(perfumeReviewBoard)
                .build();

        LikeCount likeCount = perfumeReviewBoard.getLikeCount();
        likeCount.increaseLikeCount();
        reviewLikeRepository.save(reviewLike);
    }

    @Transactional
    public void cancelLikePost(ReviewLikeRequest reviewLikeRequest) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(reviewLikeRequest.getPostId())
                .orElseThrow(ReviewPostNotFoundException::new);
        Member member = perfumeReviewBoard.getWriter();

        reviewLikeRepository.deleteReviewLikeByPostLikeMemberAndLikedPost(member, perfumeReviewBoard)
                .orElseThrow(ReviewPostNotFoundException::new);

        LikeCount likeCount = perfumeReviewBoard.getLikeCount();
        likeCount.decreaseLikeCount();
    }

    @Transactional
    public void unlikePost(ReviewLikeRequest reviewLikeRequest) {
    }

    public ReviewLikeResponse showLikeCount(Long boardId) {
        return null;
    }
}
