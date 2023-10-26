package com.example.perfume.wishlist.dto;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import lombok.Getter;

@Getter
public class WishListResponse {

    private Long wishListId;

    private Member member;

    private Perfume perfume;

    public WishListResponse() {
    }

    public WishListResponse(Long wishListId, Member member, Perfume perfume) {
        this.wishListId = wishListId;
        this.member = member;
        this.perfume = perfume;
    }

    public WishList toEntity(Perfume perfume, Member member) {
        return new WishList(member, perfume);
    }

    public static WishListResponse provideWishResponseEntity(WishList wishList) {
        return new WishListResponse(wishList.getWishListId(), wishList.getMember(), wishList.getPerfume());
    }
}
