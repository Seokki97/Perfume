package com.example.perfume.review.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.review.controller.docs.ReviewLikeControllerDocs;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.service.ReviewLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("리뷰 게시글 {}의 상태를 {}로 변경하다.", reviewLikeRequest.getPost().getBoardId(),
                reviewLikeRequest.getLikeStatus());
        return ResponseEntity.noContent().build();
    }

    @LoginCheck
    @PatchMapping("/unlike")
    public ResponseEntity<Void> unlikePost(@RequestBody ReviewLikeRequest reviewLikeRequest) {
        reviewLikeService.unlikePost(reviewLikeRequest);
        log.info("리뷰 게시글 {}의 상태를 {}로 변경하다.", reviewLikeRequest.getPost().getBoardId(),
                reviewLikeRequest.getLikeStatus());
        return ResponseEntity.noContent().build();
    }
}
