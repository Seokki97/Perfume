package com.example.perfume.review.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.review.controller.docs.ReviewLikeControllerDocs;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.service.ReviewLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class ReviewLikeController implements ReviewLikeControllerDocs {

    private final ReviewLikeService reviewLikeService;

    public ReviewLikeController(ReviewLikeService reviewLikeService) {
        this.reviewLikeService = reviewLikeService;
    }

    @LoginCheck
    @PatchMapping("/like")
    public ResponseEntity<Void> likePost(@RequestBody ReviewLikeRequest reviewLikeRequest) {
        reviewLikeService.likePost(reviewLikeRequest);

        return ResponseEntity.noContent().build();
    }

    @LoginCheck
    @PatchMapping("/unlike")
    public ResponseEntity<Void> unlikePost(@RequestBody ReviewLikeRequest reviewLikeRequest) {
        reviewLikeService.unlikePost(reviewLikeRequest);

        return ResponseEntity.noContent().build();
    }

}
