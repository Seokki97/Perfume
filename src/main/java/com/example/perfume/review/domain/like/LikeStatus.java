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
}
