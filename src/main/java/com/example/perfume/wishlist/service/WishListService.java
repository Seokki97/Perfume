package com.example.perfume.wishlist.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.dto.WishListResponse;
import com.example.perfume.wishlist.exception.WishListDuplicateException;
import com.example.perfume.wishlist.exception.WishListNotFoundException;
import com.example.perfume.wishlist.exception.WishListTooMuchException;
import com.example.perfume.wishlist.repository.WishListRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class WishListService {

    private final MemberService memberService;

    private final PerfumeService perfumeService;

    private final WishListValidation wishListValidation;

    private final WishListRepository wishListRepository;

    public WishListService(MemberService memberService, PerfumeService perfumeService,
                           WishListValidation wishListValidation, WishListRepository wishListRepository) {
        this.memberService = memberService;
        this.perfumeService = perfumeService;
        this.wishListValidation = wishListValidation;
        this.wishListRepository = wishListRepository;
    }

    public WishListResponse selectLikePerfume(WishListRequest wishListRequest) {
        Member member = memberService.findMemberById(wishListRequest.getMemberId());
        Perfume perfume = perfumeService.findPerfumeById(wishListRequest.getPerfumeId());

        if (wishListValidation.isDuplicateWishItem(wishListRequest)) {
            throw new WishListDuplicateException();
        }
        if (wishListValidation.isWishListOverMaxSize(wishListRequest)) {
            throw new WishListTooMuchException();
        }
        WishList wishList = WishList.addPerfumeToWishList(member, perfume);
        wishListRepository.save(wishList);
        return WishListResponse.provideWishResponseEntity(wishList);
    }

    public void deleteAllWishList(Long memberId) {
        if (wishListValidation.isEmptyWishList(memberId)) {
            throw new WishListNotFoundException();
        }
        wishListRepository.deleteByMemberMemberId(memberId);
    }

    public void deleteSelectedWishList(WishListRequest wishListRequest) {
        wishListRequest.isEmptyRequestBody();
        if (wishListValidation.isEmptyWishList(wishListRequest.getMemberId())) {
            throw new WishListNotFoundException();
        }
        wishListRepository.deleteByMemberMemberIdAndPerfumePerfumeId(wishListRequest.getMemberId(),
                wishListRequest.getPerfumeId());
    }

    public List<WishList> showWishList(Long memberId) {
        return wishListRepository.findByMember(memberId);
    }
}
