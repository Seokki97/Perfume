package com.example.perfume.review.domain.like;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "review_like")
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long reviewId;

    @ManyToOne
    private Member member;

    @ManyToOne
    private PerfumeReviewBoard likedPost;

    @Enumerated(EnumType.ORDINAL)
    private LikeStatus likeStatus;

    @Builder
    public ReviewLike(final Long reviewId, final Member member, final PerfumeReviewBoard likedPost, LikeStatus likeStatus) {
        this.reviewId = reviewId;
        this.member = member;
        this.likedPost = likedPost;
        this.likeStatus = likeStatus;
    }

    public LikeStatus updateLike() {
        if (this.likeStatus == LikeStatus.UNLIKE) {
            return this.likeStatus = LikeStatus.LIKE;
        }
        if (this.likeStatus == LikeStatus.LIKE) {
            return this.likeStatus = LikeStatus.CANCELED;
        }
        return this.likeStatus = LikeStatus.LIKE;
    }

    public LikeStatus updateUnLike() {
        if (this.likeStatus == LikeStatus.LIKE) {
            return this.likeStatus = LikeStatus.UNLIKE;
        }
        if (this.likeStatus == LikeStatus.UNLIKE) {
            return this.likeStatus = LikeStatus.CANCELED;
        }
        return this.likeStatus = LikeStatus.UNLIKE;
    }
}
