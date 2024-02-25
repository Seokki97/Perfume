package com.example.perfume.review.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.review.domain.like.PostLike;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.LikeCount;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.exception.AlreadyPushLikeException;
import com.example.perfume.review.exception.LikedPostNotFoundException;
import com.example.perfume.review.exception.ReviewPostNotFoundException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.repository.ReviewLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewBoardRepository reviewBoardRepository;
    private final MemberRepository memberRepository;

    public ReviewLikeService(ReviewLikeRepository reviewLikeRepository, ReviewBoardRepository reviewBoardRepository,
                             MemberRepository memberRepository) {
        this.reviewLikeRepository = reviewLikeRepository;
        this.reviewBoardRepository = reviewBoardRepository;
        this.memberRepository = memberRepository;
    }

    public void pushLikeOrUnlike(ReviewLikeRequest reviewLikeRequest) {
        PerfumeReviewBoard reviewPost = validateAlreadyPushLike(reviewLikeRequest);
        ReviewLike reviewLike = ReviewLike.builder()
                .postLike(new PostLike(reviewPost.getWriter(), reviewLikeRequest.getLikeStatus()))
                .likedPost(reviewPost)
                .build();
        LikeCount likeCount = reviewPost.getLikeCount();
        likeCount.calculatePushButton(reviewLike.getPostLike());
        reviewLikeRepository.save(reviewLike);
    }

    public void cancelLikePost(ReviewLikeRequest reviewLikeRequest) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(reviewLikeRequest.getPostId())
                .orElseThrow(ReviewPostNotFoundException::new);
        Member member = memberRepository.findByMemberId(reviewLikeRequest.getMemberId())
                .orElseThrow(UserNotFoundException::new);

        if (!isExistPushedData(reviewLikeRequest)) {
            throw new LikedPostNotFoundException();
        }
        reviewLikeRepository.deleteReviewLikeByPostLikeMemberAndLikedPost(member, perfumeReviewBoard)
                .orElseThrow(ReviewPostNotFoundException::new);

        LikeCount likeCount = perfumeReviewBoard.getLikeCount();
        likeCount.decreaseLikeCount();
    }

    public PerfumeReviewBoard validateAlreadyPushLike(ReviewLikeRequest reviewLikeRequest) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(reviewLikeRequest.getPostId())
                .orElseThrow(ReviewPostNotFoundException::new);
        if (isExistPushedData(reviewLikeRequest)) {
            throw new AlreadyPushLikeException();
        }
        return perfumeReviewBoard;
    }

    public boolean isExistPushedData(ReviewLikeRequest reviewLikeRequest) {
        return reviewLikeRepository.existsByLikedPostBoardIdAndPostLikeMemberMemberId(
                reviewLikeRequest.getPostId(),
                reviewLikeRequest.getMemberId());
    }
}
