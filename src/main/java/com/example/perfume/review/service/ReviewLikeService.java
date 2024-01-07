package com.example.perfume.review.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.review.domain.like.LikeStatus;
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
    private final MemberService memberService;


    public ReviewLikeService(ReviewLikeRepository reviewLikeRepository, ReviewBoardRepository reviewBoardRepository,
                             MemberService memberService) {
        this.reviewLikeRepository = reviewLikeRepository;
        this.reviewBoardRepository = reviewBoardRepository;
        this.memberService = memberService;
    }

    @Transactional
    public void likePost(ReviewLikeRequest reviewLikeRequest) {
        Member member = memberService.findByMemberPk(reviewLikeRequest.getMemberId());
        PerfumeReviewBoard reviewedPost = reviewBoardRepository.findByBoardId(reviewLikeRequest.getPostId())
                .orElseThrow(ReviewPostNotFoundException::new);
        ReviewLike reviewLike = ReviewLike.builder()
                .member(member)
                .likedPost(reviewedPost)
                .likeStatus(LikeStatus.LIKE)
                .build();
        reviewLikeRepository.save(reviewLike);
        reviewedPost.likePost(reviewLike);
    }

    @Transactional
    public void unlikePost(ReviewLikeRequest reviewLikeRequest) {
    }

    public boolean isAlreadyPushLikeOrUnlike(ReviewLikeRequest reviewLikeRequest) {
        return true;
    }


    public ReviewLikeResponse showLikeCount(Long boardId) {
        return null;
    }
}
