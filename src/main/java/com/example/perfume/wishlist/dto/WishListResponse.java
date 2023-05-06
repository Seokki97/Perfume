package com.example.perfume.wishlist.dto;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
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

    public WishList toEntity(Perfume perfume, Member member) {
        return WishList.builder()
                .member(member)
                .perfume(perfume)
                .build();
    }

    public static WishListResponse provideWishResponseEntity(WishList wishList) {
        return WishListResponse.builder()
                .wishListId(wishList.getId())
                .perfume(wishList.getPerfume())
                .member(wishList.getMember())
                .build();
    }
}
