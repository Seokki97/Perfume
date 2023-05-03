package com.example.perfume.wishlist.dto;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WishListResponse {

    private Long wishListId;

    private Member member;

    private Perfume perfume;

    public WishListResponse() {
    }

    @Builder
    public WishListResponse(Long wishListId, Member member, Perfume perfume) {
        this.wishListId = wishListId;
        this.member = member;
        this.perfume = perfume;
    }
}
