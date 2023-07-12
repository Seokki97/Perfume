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
    private Long review_id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private PerfumeReviewBoard likedPost;

    @Enumerated(EnumType.STRING)
    private LikeStatus likeStatus;

    @Builder
    public ReviewLike(final Long review_id, final Member member, final PerfumeReviewBoard likedPost, final LikeStatus likeStatus) {
        this.review_id = review_id;
        this.member = member;
        this.likedPost = likedPost;
        this.likeStatus = likeStatus;
    }

    public void updateLike() {
        if (this.likeStatus != LikeStatus.LIKE) {
            this.likeStatus = LikeStatus.LIKE;
            this.likedPost.increaseLikeCount();
        }
    }

    public void updateUnlike() {
        if (this.likeStatus != LikeStatus.CANCELED) {
            this.likeStatus = LikeStatus.CANCELED;
            this.likedPost.decreaseLikeCount();
        }
    }

}
