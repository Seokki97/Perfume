package com.example.perfume.review.controller;

import com.example.perfume.review.controller.docs.ReviewAnalyzeControllerDocs;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.service.ReviewAnalyzeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class ReviewAnalyzeController implements ReviewAnalyzeControllerDocs {
    private final ReviewAnalyzeService reviewAnalyzeService;

    public ReviewAnalyzeController(ReviewAnalyzeService reviewAnalyzeService) {
        this.reviewAnalyzeService = reviewAnalyzeService;
    }


    @GetMapping("/show-most-liked-perfume")
    public ResponseEntity<List<PerfumeReviewBoard>> showMostLikedPerfume() {

        return ResponseEntity.ok(reviewAnalyzeService.sortByMostLikeReviews());
    }

    @GetMapping("/show-most-unliked-perfume")
    public ResponseEntity<List<PerfumeReviewBoard>> showMostUnlikedPerfume(){

        return ResponseEntity.ok(reviewAnalyzeService.sortByMostUnlikeReviews());
    }
}
