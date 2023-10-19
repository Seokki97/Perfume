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
    public void updateStatus(LikeStatus status){
        if(status == LikeStatus.LIKE){
            this.likeStatus = LikeStatus.updateLike(status);
        }
        if(status == LikeStatus.UNLIKE){
            this.likeStatus = LikeStatus.updateUnLike(status);
        }
    }
}
