package com.example.perfume.wishlist.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.dto.WishListResponse;
import com.example.perfume.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WishListUtil {
    private final WishListRepository wishListRepository;

    public WishListUtil(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public WishList addPerfumeToWishList(Member member, Perfume perfume) {
        return WishList.builder()
                .perfume(perfume)
                .member(member)
                .build();
    }

    public WishListResponse provideWishResponseEntity(WishList wishList) {
        return WishListResponse.builder()
                .wishListId(wishList.getId())
                .perfume(wishList.getPerfume())
                .member(wishList.getMember())
                .build();
    }

    public void saveWishPerfume(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> showWishList(Long memberId) {
        return wishListRepository.findByMemberId(memberId);
    }

    public boolean isDuplicateWishItem(WishListRequest wishListRequest) {
        return showWishList(wishListRequest.getMemberId()).stream()
                .anyMatch(perfume -> Objects.equals(perfume.getPerfume().getId(), wishListRequest.getPerfumeId()));
    }

}
