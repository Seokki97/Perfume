package com.example.perfume.wishlist.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.wishlist.controller.docs.WishListControllerDocs;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.dto.WishListResponse;
import com.example.perfume.wishlist.service.WishListService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member/wish")
public class WishListController implements WishListControllerDocs {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @LoginCheck
    @PostMapping("/select-wish-perfume")
    public ResponseEntity<WishListResponse> selectLikePerfume(@RequestBody final WishListRequest wishListRequest) {
        log.info("향수 위시리스트에 담기, Member Id : {}, Perfume Id : {}", wishListRequest.getMemberId(),
                wishListRequest.getPerfumeId());
        return ResponseEntity.ok(wishListService.selectLikePerfume(wishListRequest));
    }

    @LoginCheck
    @GetMapping("/show-list/{memberId}")
    public ResponseEntity<List<WishList>> showWishList(@PathVariable("memberId") final Long memberId) {
        log.info("향수 리스트 조회하기, Member Id :{}", memberId);
        return ResponseEntity.ok(wishListService.showWishList(memberId));
    }

    @LoginCheck
    @DeleteMapping("/delete-all-element/{memberId}")
    public ResponseEntity<Void> deleteAllWishedPerfume(@PathVariable("memberId") final Long memberId) {
        log.info("향수 리스트 전체 삭제하기, Member Id: {}", memberId);
        wishListService.deleteAllWishList(memberId);
        return ResponseEntity.noContent().build();
    }

    @LoginCheck
    @DeleteMapping("/delete-selected-perfume")
    public ResponseEntity<Void> deleteSelectedPerfume(@RequestBody WishListRequest wishListRequest) {
        log.info("향수 리스트 선택 삭제하기, Member Id: {}, Perfume Id: {}", wishListRequest.getMemberId(),
                wishListRequest.getPerfumeId());
        wishListService.deleteSelectedWishList(wishListRequest);
        return ResponseEntity.noContent().build();
    }
}
