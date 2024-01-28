package com.example.perfume.review.controller;

import com.example.perfume.review.controller.docs.ReviewAnalyzeControllerDocs;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.service.ReviewAnalyzeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/board")
public class ReviewAnalyzeController implements ReviewAnalyzeControllerDocs {
    private final ReviewAnalyzeService reviewAnalyzeService;

    public ReviewAnalyzeController(ReviewAnalyzeService reviewAnalyzeService) {
        this.reviewAnalyzeService = reviewAnalyzeService;
    }

    @GetMapping("/show-like-ranking")
    public ResponseEntity<List<PerfumeReviewBoard>> showMostLikedPerfume() {

        return ResponseEntity.ok(reviewAnalyzeService.sortLikeReviews());
    }

    @GetMapping("/show-unlike-ranking")
    public ResponseEntity<List<PerfumeReviewBoard>> showMostUnlikedPerfume(
            @RequestParam(value = "perfumeName", required = false) String perfumeName) {
        if (perfumeName == null) {
            log.info("리뷰수에 따른 순위를 조회한다.");
            return ResponseEntity.ok(reviewAnalyzeService.sortUnlikeReviews());
        }
        log.info("향수 이름 : {} 에 대한 순위를 조회한다.", perfumeName);
        return ResponseEntity.ok(reviewAnalyzeService.sortUnlikeReviewsFromSelectedPerfume(perfumeName));
    }
}
