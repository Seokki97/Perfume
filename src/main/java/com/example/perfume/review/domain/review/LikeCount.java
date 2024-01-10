package com.example.perfume.review.domain.review;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class LikeCount {

    private Long likeCount;
    private Long unlikeCount;

    public LikeCount(Long likeCount, Long unlikeCount) {
        this.likeCount = likeCount;
        this.unlikeCount = unlikeCount;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

    public void increaseUnlikeCount() {
        this.unlikeCount++;
    }

    public void decreaseUnlikeCount() {
        this.unlikeCount--;
    }
}
