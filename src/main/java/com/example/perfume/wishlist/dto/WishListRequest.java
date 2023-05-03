package com.example.perfume.wishlist.dto;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
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