package com.example.perfume.wishlist.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WishListRequest {

    private Long wishListId;

    private Long memberId;

    private Long perfumeId;

    public WishListRequest() {
    }

    @Builder
    public WishListRequest(Long wishListId, Long memberId, Long perfumeId) {
        this.wishListId = wishListId;
        this.memberId = memberId;
        this.perfumeId = perfumeId;
    }
}
