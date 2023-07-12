package com.example.perfume.review.service;

import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewLikeService {

    public void likePost(ReviewLikeRequest reviewLikeRequest) {
        ReviewLike reviewLike = ReviewLike.builder()
                .likedPost(reviewLikeRequest.getPostId())
                .member(reviewLikeRequest.getMemberId())
                .build();

        reviewLike.updateLike();

    }

    public void unlikePost(ReviewLikeRequest reviewLikeRequest){

        ReviewLike reviewLike = ReviewLike.builder()
                .likedPost(reviewLikeRequest.getPostId())
                .member(reviewLikeRequest.getMemberId())
                .build();

        reviewLike.updateUnlike();
    }
}
