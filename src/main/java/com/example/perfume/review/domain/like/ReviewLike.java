package com.example.perfume.review.domain.like;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "review_like")
public class ReviewLike {
    //게시글당 좋아요 실헝요가 하나씩 생성되어야함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    private PerfumeReviewBoard likedPost;

    @Embedded
    private PostLike postLike;

    @Builder
    public ReviewLike(final Long reviewId, final PerfumeReviewBoard likedPost, PostLike postLike) {
        this.reviewId = reviewId;
        this.likedPost = likedPost;
        this.postLike = postLike;
    }


}
