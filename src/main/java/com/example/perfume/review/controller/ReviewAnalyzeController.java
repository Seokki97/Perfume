package com.example.perfume.review.controller;

import com.example.perfume.review.controller.docs.ReviewAnalyzeControllerDocs;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.responseDto.ReviewAnalyzeResponse;
import com.example.perfume.review.service.ReviewAnalyzeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api//board")
public class ReviewAnalyzeController implements ReviewAnalyzeControllerDocs {
    private final ReviewAnalyzeService reviewAnalyzeService;

    public ReviewAnalyzeController(ReviewAnalyzeService reviewAnalyzeService) {
        this.reviewAnalyzeService = reviewAnalyzeService;
    }

    @GetMapping("/show-like-ranking")
    public ResponseEntity<ReviewAnalyzeResponse> showMostLikedPerfume() {
        log.info("좋아요가 많은 리뷰 게시글 순으로 조회한다.");
        return ResponseEntity.ok(reviewAnalyzeService.sortLikeReviews());
    }

    @GetMapping("/show-unlike-ranking")
    public ResponseEntity<List<PerfumeReviewBoard>> showMostUnlikedPerfume() {
        log.info("싫어요가 많은 리뷰 게시글 순으로 조회한다");
        return ResponseEntity.ok(reviewAnalyzeService.sortUnlikeReviews());
    }
}
