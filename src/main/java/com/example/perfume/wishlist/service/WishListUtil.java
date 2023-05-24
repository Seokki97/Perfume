package com.example.perfume.wishlist.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
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

    public WishList addPerfumeToWishList(Member member, Perfume perfume) {
        return WishList.builder()
                .perfume(perfume)
                .member(member)
                .build();
    }

    public boolean isExistsWishList(WishListRequest wishListRequest) {
        return wishListRepository.existsByMemberIdAndPerfumeId(wishListRequest.getMemberId(), wishListRequest.getPerfumeId());
    }

    public void saveWishPerfume(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> showWishList(Long memberId) {
        return wishListRepository.findByMemberId(memberId);
    }

    public boolean isEmptyWishList(Long memberId) {
        return wishListRepository.findByMemberId(memberId).isEmpty();
    }
    public boolean isEmptyRequestBody(WishListRequest wishListRequest){
        return wishListRequest.getMemberId() == null && wishListRequest.getPerfumeId() == null;
    }

    public boolean isDuplicateWishItem(WishListRequest wishListRequest) {
        return wishListRepository.findByMemberId(wishListRequest.getMemberId()).stream()
                .anyMatch(perfume -> Objects.equals(perfume.getPerfume().getId(), wishListRequest.getPerfumeId()));
    }

    public boolean isWishListOverMaxSize(WishListRequest wishListRequest) {
        return wishListRepository.findByMemberId(wishListRequest.getMemberId()).size() > MAX_WISH_SIZE;
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
