package com.example.perfume.review.dto.like;

import com.example.perfume.review.domain.like.LikeStatus;
import lombok.Getter;

@Getter
public class ReviewLikeRequest {

    private Long memberId;

    private Long postId;

    private LikeStatus likeStatus;

    public ReviewLikeRequest() {
    }

    public ReviewLikeRequest(Long memberId, Long postId, LikeStatus likeStatus) {
        this.memberId = memberId;
        this.postId = postId;
        this.likeStatus = likeStatus;
    }
}
