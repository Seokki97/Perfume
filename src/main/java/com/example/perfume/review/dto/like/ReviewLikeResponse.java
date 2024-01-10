package com.example.perfume.review.dto.like;

import com.example.perfume.review.domain.like.PostLike;

public class ReviewLikeResponse {

    private PostLike postLike;

    public ReviewLikeResponse(final PostLike postLike) {
        this.postLike = postLike;
    }

}
