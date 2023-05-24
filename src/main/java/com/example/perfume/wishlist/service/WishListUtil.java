package com.example.perfume.wishlist.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.exception.WishListDuplicateException;
import com.example.perfume.wishlist.exception.WishListNotFoundException;
import com.example.perfume.wishlist.exception.WishListTooMuchException;
import com.example.perfume.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class WishListUtil {
    private static final int MAX_WISH_SIZE = 14;

    private final WishListRepository wishListRepository;

    public WishListUtil(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void validateExistsWishList(WishListRequest wishListRequest) {
        if (wishListRepository.existsByMemberIdAndPerfumeId(wishListRequest.getMemberId(), wishListRequest.getPerfumeId())) {
            throw new WishListNotFoundException();
        }
    }

    public void saveWishPerfume(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> showWishList(Long memberId) {
        return wishListRepository.findByMemberId(memberId);
    }

    public void validateEmptyWishList(Long memberId) {
        if (wishListRepository.findByMemberId(memberId).isEmpty()) {
            throw new WishListNotFoundException();
        }
    }

    public boolean isEmptyRequestBody(WishListRequest wishListRequest) {
        return wishListRequest.getMemberId() == null && wishListRequest.getPerfumeId() == null;
    }

    public void validateDuplicateWishItem(WishListRequest wishListRequest) {
        boolean wishItemDuplicate = wishListRepository.findByMemberId(wishListRequest.getMemberId()).stream()
                .anyMatch(perfume -> Objects.equals(perfume.getPerfume().getId(), wishListRequest.getPerfumeId()));

        if (wishItemDuplicate) {
            throw new WishListDuplicateException();
        }
    }

    public void validateWishListOverMaxSize(WishListRequest wishListRequest) {
        boolean wishListOverSize = wishListRepository.findByMemberId(wishListRequest.getMemberId()).size() > MAX_WISH_SIZE;
        if (wishListOverSize) {
            throw new WishListTooMuchException();
        }
    }

    @Transactional
    public void deleteAllWishedList(Long memberId) {
        wishListRepository.deleteByMemberId(memberId);
    }

    @Transactional
    public void deleteSelectedWishElement(WishListRequest wishListRequest) {
        wishListRepository.deleteByMemberIdAndPerfumeId(wishListRequest.getMemberId(), wishListRequest.getPerfumeId());
    }
}
