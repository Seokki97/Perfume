package com.example.perfume.wishlist.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WishListRequest {

    private Long memberId;

    private Long perfumeId;

    public WishListRequest() {
    }

    @Builder
    public WishListRequest(Long memberId, Long perfumeId) {
        this.memberId = memberId;
        this.perfumeId = perfumeId;
    }
}
