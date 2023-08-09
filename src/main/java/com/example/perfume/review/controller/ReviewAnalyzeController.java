package com.example.perfume.review.controller;

import com.example.perfume.review.controller.docs.ReviewAnalyzeControllerDocs;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.service.ReviewAnalyzeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class ReviewAnalyzeController implements ReviewAnalyzeControllerDocs {
    private final ReviewAnalyzeService reviewAnalyzeService;

    public ReviewAnalyzeController(ReviewAnalyzeService reviewAnalyzeService) {
        this.reviewAnalyzeService = reviewAnalyzeService;
    }

    @GetMapping("/show-like-ranking")
    public ResponseEntity<List<PerfumeReviewBoard>> showMostLikedPerfume(@RequestParam(value = "perfumeName", required = false) String perfumeName) {
        if (perfumeName == null) {
            return ResponseEntity.ok(reviewAnalyzeService.sortLikeReviews());
        }
        return ResponseEntity.ok(reviewAnalyzeService.sortLikeReviewsFromSelectedPerfume(perfumeName));
    }

    @GetMapping("/show-unlike-ranking")
    public ResponseEntity<List<PerfumeReviewBoard>> showMostUnlikedPerfume(@RequestParam(value = "perfumeName", required = false) String perfumeName) {
        if (perfumeName == null) {
            return ResponseEntity.ok(reviewAnalyzeService.sortUnlikeReviews());
        }
        return ResponseEntity.ok(reviewAnalyzeService.sortLikeReviewsFromSelectedPerfume(perfumeName));
    }

}
