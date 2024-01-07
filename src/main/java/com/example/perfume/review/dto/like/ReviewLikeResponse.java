package com.example.perfume.review.dto.like;

public class ReviewLikeResponse {

    private Long likeCount;
    private Long unlikeCount;

    public ReviewLikeResponse(Long likeCount, Long unlikeCount) {
        this.likeCount = likeCount;
        this.unlikeCount = unlikeCount;
    }

}
