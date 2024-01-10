package com.example.perfume.review.dto.like;

import lombok.Getter;

@Getter
public class ReviewLikeRequest {

    private Long memberId;

    private Long postId;

    public ReviewLikeRequest() {
    }

    public ReviewLikeRequest(Long memberId, Long postId) {
        this.memberId = memberId;
        this.postId = postId;
    }
}
