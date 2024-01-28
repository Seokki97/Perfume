package com.example.perfume.review.controller;

import com.example.perfume.review.controller.docs.ReviewLikeControllerDocs;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.service.ReviewLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/board")
public class ReviewLikeController implements ReviewLikeControllerDocs {

    private final ReviewLikeService reviewLikeService;

    public ReviewLikeController(ReviewLikeService reviewLikeService) {
        this.reviewLikeService = reviewLikeService;
    }

    //@LoginCheck
    @PatchMapping("/push")
    public ResponseEntity<Void> pushLikeOrUnlike(@RequestBody ReviewLikeRequest reviewLikeRequest) {
        reviewLikeService.pushLikeOrUnlike(reviewLikeRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/push/cancel")
    public ResponseEntity<Void> cancelLike(@RequestBody ReviewLikeRequest reviewLikeRequest) {
        reviewLikeService.cancelLikePost(reviewLikeRequest);
        return ResponseEntity.noContent().build();
    }
}
