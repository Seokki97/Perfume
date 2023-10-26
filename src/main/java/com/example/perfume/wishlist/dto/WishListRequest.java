package com.example.perfume.wishlist.dto;

import lombok.Getter;

@Getter
public class WishListRequest {

    private Long memberId;

    private Long perfumeId;

    public WishListRequest() {
    }

    public WishListRequest(Long memberId, Long perfumeId) {
        this.memberId = memberId;
        this.perfumeId = perfumeId;
    }
}
