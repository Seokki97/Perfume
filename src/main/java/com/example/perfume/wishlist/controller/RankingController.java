package com.example.perfume.wishlist.controller;

import com.example.perfume.wishlist.dto.RankingResponse;
import com.example.perfume.wishlist.service.WishListAnalyze;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/member/wish")
public class RankingController {
    private final WishListAnalyze wishListAnalyze;

    public RankingController(WishListAnalyze wishListAnalyze) {
        this.wishListAnalyze = wishListAnalyze;
    }

    @GetMapping("/show-ranking")
    public ResponseEntity<List<RankingResponse>> showRanking() {
        return ResponseEntity.ok(wishListAnalyze.showTopRankedPerfumeList());
    }

}
