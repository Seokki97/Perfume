package com.example.perfume.wishlist.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.wishlist.controller.docs.WishListControllerDocs;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.dto.WishListResponse;
import com.example.perfume.wishlist.service.WishListService;
import com.example.perfume.wishlist.service.WishListUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/wish")
public class WishListController implements WishListControllerDocs {

    private final WishListService wishListService;

    private final WishListUtil wishListUtil;

    public WishListController(WishListService wishListService, WishListUtil wishListUtil) {
        this.wishListUtil = wishListUtil;
        this.wishListService = wishListService;
    }

    @LoginCheck
    @PostMapping("/select-wish-perfume")
    public ResponseEntity<WishListResponse> selectLikePerfume(@RequestBody WishListRequest wishListRequest) {
        return ResponseEntity.ok(wishListService.selectLikePerfume(wishListRequest));
    }

    @LoginCheck
    @GetMapping("/show-list/{memberId}")
    public ResponseEntity<List<WishList>> showWishList(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(wishListUtil.showWishList(memberId));
    }

    @LoginCheck
    @DeleteMapping("/delete-all-element/{memberId}")
    public ResponseEntity<Void> deleteAllWishedPerfume(@PathVariable("memberId") Long memberId) {
        wishListService.deleteAllWishList(memberId);

        return ResponseEntity.noContent().build();
    }

    @LoginCheck
    @DeleteMapping("/delete-selected-perfume")
    public ResponseEntity<Void> deleteSelectedPerfume(@RequestBody WishListRequest wishListRequest) {
        wishListService.deleteSelectedWishList(wishListRequest);

        return ResponseEntity.noContent().build();
    }
}
