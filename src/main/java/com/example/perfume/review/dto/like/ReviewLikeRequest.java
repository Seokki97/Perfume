package com.example.perfume.review.dto.like;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewLikeRequest {

    private Member member;

    private PerfumeReviewBoard post;

    private LikeStatus likeStatus;

    @Builder
    public ReviewLikeRequest(Member memberId, PerfumeReviewBoard postId, LikeStatus likeStatus) {
        this.member = memberId;
        this.post = postId;
        this.likeStatus = likeStatus;
    }

    public ReviewLike toEntity() {
        return ReviewLike.builder()
                .likedPost(post)
                .likeStatus(likeStatus)
                .member(member)
                .build();
    }

    public Long getMemberId() {
        return member.getMemberId();
    }

    public Long getPostId() {
        return post.getBoardId();
    }
}
