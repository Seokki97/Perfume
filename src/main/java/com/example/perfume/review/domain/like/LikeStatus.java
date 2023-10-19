package com.example.perfume.review.domain.like;

import lombok.Getter;

@Getter
public enum LikeStatus {
    LIKE("LIKE"),
    CANCELED("CANCELED"),
    UNLIKE("UNLIKE");

    private final String status;

    LikeStatus(String status) {
        this.status = status;
    }

    public static LikeStatus updateLike(LikeStatus status) {
        if (status == UNLIKE) {
            return LikeStatus.LIKE;
        }
        if (status == LIKE) {
            return LikeStatus.CANCELED;
        }
        return LikeStatus.LIKE;
    }

    public static LikeStatus updateUnLike(LikeStatus status) {
        if (status == LIKE) {
            return LikeStatus.UNLIKE;
        }
        if (status == UNLIKE) {
            return  LikeStatus.CANCELED;
        }
        return LikeStatus.UNLIKE;
    }
}
