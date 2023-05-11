package com.example.perfume.wishlist.controller;

import com.example.perfume.wishlist.controller.docs.RankingListDocs;
import com.example.perfume.wishlist.dto.RankingResponse;
import com.example.perfume.wishlist.service.WishListAnalyze;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member/wish")
public class RankingController implements RankingListDocs {
    private final WishListAnalyze wishListAnalyze;

    public RankingController(WishListAnalyze wishListAnalyze) {
        this.wishListAnalyze = wishListAnalyze;
    }

    @GetMapping("/show-ranking")
    public ResponseEntity<List<RankingResponse>> showRanking() {
        log.info("WishList 랭킹 조회하기 요청");
        return ResponseEntity.ok(wishListAnalyze.showTopRankedPerfumeList());
    }

}
