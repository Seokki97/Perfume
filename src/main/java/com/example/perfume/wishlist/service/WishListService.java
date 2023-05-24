package com.example.perfume.wishlist.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.dto.WishListResponse;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private final MemberService memberService;

    private final PerfumeService perfumeService;

    private final WishListUtil wishListUtil;

    public WishListService(MemberService memberService, PerfumeService perfumeService, WishListUtil wishListUtil) {
        this.memberService = memberService;
        this.perfumeService = perfumeService;
        this.wishListUtil = wishListUtil;
    }

    public void makeMokData(Long perfumeId) {
        Perfume perfume = perfumeService.findPerfumeById(perfumeId);

        WishList wishList = WishList.builder()
                .perfume(perfume)
                .build();
        wishListUtil.saveWishPerfume(wishList);
    }

    public WishListResponse selectLikePerfume(WishListRequest wishListRequest) {
        wishListUtil.isEmptyRequestBody(wishListRequest);

        Member member = memberService.findMemberById(wishListRequest.getMemberId());
        Perfume perfume = perfumeService.findPerfumeById(wishListRequest.getPerfumeId());

        wishListUtil.validateDuplicateWishItem(wishListRequest);

        wishListUtil.validateWishListOverMaxSize(wishListRequest);

        WishList wishList = WishList.addPerfumeToWishList(member, perfume);
        wishListUtil.saveWishPerfume(wishList);

        return WishListResponse.provideWishResponseEntity(wishList);
    }

    public void deleteAllWishList(Long memberId) {
        wishListUtil.validateEmptyWishList(memberId);

        wishListUtil.deleteAllWishedList(memberId);
    }

    public void deleteSelectedWishList(WishListRequest wishListRequest) {
        wishListUtil.isEmptyRequestBody(wishListRequest);

        wishListUtil.validateEmptyWishList(wishListRequest.getMemberId());
        wishListUtil.validateExistsWishList(wishListRequest);

        wishListUtil.deleteSelectedWishElement(wishListRequest);
    }

}
