package com.example.perfume.review.controller;

import com.example.perfume.review.controller.docs.ReviewBoardControllerDocs;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.requestDto.PostDeleteRequest;
import com.example.perfume.review.dto.review.requestDto.PostUpdateRequest;
import com.example.perfume.review.dto.review.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.review.responseDto.ReviewBoardResponse;
import com.example.perfume.review.service.ReviewBoardService;
import com.example.perfume.member.service.jwt.LoginCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class ReviewBoardController implements ReviewBoardControllerDocs {

    private final ReviewBoardService reviewBoardService;

    public ReviewBoardController(ReviewBoardService reviewBoardService) {
        this.reviewBoardService = reviewBoardService;
    }

    @LoginCheck
    @PostMapping("/write/{memberId}")
    public ResponseEntity<ReviewBoardResponse> writeReview(@PathVariable final Long memberId, @RequestBody final ReviewBoardRequest reviewBoardRequest) {

        return ResponseEntity.ok(reviewBoardService.writeReview(memberId, reviewBoardRequest));
    }

    //상태코드 200 300 400 500

    @LoginCheck
    @PatchMapping("/update")
    public ResponseEntity<ReviewBoardResponse> updatePost(@RequestBody final PostUpdateRequest postUpdateRequest) {

        return ResponseEntity.ok().body(reviewBoardService.modifyReviewTitleAndContent(postUpdateRequest));
    }

    @LoginCheck
    @PatchMapping("/update-title")
    public ResponseEntity<ReviewBoardResponse> updateTitle(@RequestBody final PostUpdateRequest postUpdateRequest) {
        return ResponseEntity.ok().body(reviewBoardService.modifyReviewTitle(postUpdateRequest));
    }

    @LoginCheck
    @PatchMapping("/update-content")
    public ResponseEntity<ReviewBoardResponse> updateContent(@RequestBody final PostUpdateRequest postUpdateRequest) {
        return ResponseEntity.ok().body(reviewBoardService.modifyReviewContent(postUpdateRequest));
    }

    @LoginCheck
    @DeleteMapping("/delete-post")
    public ResponseEntity<Long> deletePost(@RequestBody final PostDeleteRequest postDeleteRequest) {

        return ResponseEntity.ok(reviewBoardService.deleteReviewPost(postDeleteRequest));
    }

    @GetMapping("/show-post/{boardId}")
    public ResponseEntity<ReviewBoardResponse> showPost(@PathVariable final Long boardId) {

        return ResponseEntity.ok(reviewBoardService.showPost(boardId));
    }

    @GetMapping("/show-searched-posts")
    public ResponseEntity<List<PerfumeReviewBoard>> showSearchedPosts(@RequestParam final String content) {
        return ResponseEntity.ok(reviewBoardService.showSearchedPosts(content));
    }

    @GetMapping("/show-my-posts/{memberId}")
    public ResponseEntity<List<PerfumeReviewBoard>> showMyPosts(@PathVariable final Long memberId) {
        return ResponseEntity.ok(reviewBoardService.showMyReviewPost(memberId));
    }

}
