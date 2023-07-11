package com.example.perfume.board.controller;

import com.example.perfume.board.domain.review.PerfumeReviewBoard;
import com.example.perfume.board.dto.requestDto.PostDeleteRequest;
import com.example.perfume.board.dto.requestDto.PostUpdateRequest;
import com.example.perfume.board.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.board.dto.responseDto.ReviewBoardResponse;
import com.example.perfume.board.service.ReviewBoardService;
import com.example.perfume.member.service.jwt.LoginCheck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class ReviewBoardController {

    private final ReviewBoardService reviewBoardService;

    public ReviewBoardController(ReviewBoardService reviewBoardService) {
        this.reviewBoardService = reviewBoardService;
    }

    @LoginCheck
    @PostMapping("/write/{memberId}")
    public ResponseEntity<ReviewBoardResponse> writeReview(@PathVariable final Long memberId, @RequestBody ReviewBoardRequest reviewBoardRequest) {

        return ResponseEntity.ok(reviewBoardService.writeReview(memberId, reviewBoardRequest));
    }

    @LoginCheck
    @PatchMapping("/update")
    public ResponseEntity<ReviewBoardResponse> updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {

        return ResponseEntity.ok().body(reviewBoardService.modifyReview(postUpdateRequest));
    }

    @LoginCheck
    @DeleteMapping("/delete-post")
    public ResponseEntity<Long> deletePost(@RequestBody PostDeleteRequest postDeleteRequest) {

        return ResponseEntity.ok(reviewBoardService.deleteReviewPost(postDeleteRequest));
    }

    @GetMapping("/show-post/{boardId}")
    public ResponseEntity<ReviewBoardResponse> showPost(@PathVariable final Long boardId) {

        return ResponseEntity.ok(reviewBoardService.showPost(boardId));
    }

    @GetMapping("/show-searched-posts")
    public ResponseEntity<List<PerfumeReviewBoard>> showSearchedPosts(@RequestParam String content) {
        return ResponseEntity.ok(reviewBoardService.showSearchedPosts(content));
    }

}
