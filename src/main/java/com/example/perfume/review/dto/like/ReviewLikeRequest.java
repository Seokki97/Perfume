package com.example.perfume.review.dto.like;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import lombok.Getter;

@Getter
public class ReviewLikeRequest {

    private Member memberId;

    private PerfumeReviewBoard postId;

    private LikeStatus likeStatus;

    public ReviewLikeRequest(Member memberId, PerfumeReviewBoard postId, LikeStatus likeStatus) {
        this.memberId = memberId;
        this.postId = postId;
        this.likeStatus = likeStatus;
    }
}
