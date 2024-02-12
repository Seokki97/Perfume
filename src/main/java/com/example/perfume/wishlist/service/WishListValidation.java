package com.example.perfume.wishlist.service;

import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.repository.WishListRepository;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class WishListValidation {

    private static final int MAX_WISH_SIZE = 14;

    private final WishListRepository wishListRepository;

    public WishListValidation(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public boolean isEmptyWishList(Long memberId) {
        return wishListRepository.findByMember(memberId).isEmpty();
    }

    public boolean isDuplicateWishItem(WishListRequest wishListRequest) {
        return wishListRepository.findByMember(wishListRequest.getMemberId()).stream()
                .anyMatch(
                        perfume -> Objects.equals(perfume.getPerfumeId(), wishListRequest.getPerfumeId()));
    }

    public boolean isWishListOverMaxSize(WishListRequest wishListRequest) {
        return wishListRepository.findByMember(wishListRequest.getMemberId()).size() > MAX_WISH_SIZE;
    }
}
