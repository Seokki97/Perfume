package com.example.perfume.wishlist.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.dto.WishListResponse;
import com.example.perfume.wishlist.exception.WishListDuplicateException;
import com.example.perfume.wishlist.repository.WishListRepository;
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

    public WishListResponse selectLikePerfume(WishListRequest wishListRequest) {
        Member member = memberService.findMemberById(wishListRequest.getMemberId());
        Perfume perfume = perfumeService.findPerfumeById(wishListRequest.getPerfumeId());
        WishList wishList = wishListUtil.addPerfumeToWishList(member, perfume);

        if (wishListUtil.isDuplicateWishItem(wishListRequest)) {
            throw new WishListDuplicateException();
        }
        wishListUtil.saveWishPerfume(wishList);

        return wishListUtil.provideWishResponseEntity(wishList);
    }

}
