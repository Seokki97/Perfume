package com.example.perfume.review.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.review.controller.docs.ReviewBoardControllerDocs;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.requestDto.PostDeleteRequest;
import com.example.perfume.review.dto.review.requestDto.PostUpdateRequest;
import com.example.perfume.review.dto.review.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.review.responseDto.ReviewBoardResponse;
import com.example.perfume.review.service.ReviewBoardService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/board")
public class ReviewBoardController implements ReviewBoardControllerDocs {

    private final ReviewBoardService reviewBoardService;

    public ReviewBoardController(ReviewBoardService reviewBoardService) {
        this.reviewBoardService = reviewBoardService;
    }

    //@LoginCheck
    @PostMapping("/write/{memberId}")
    public ResponseEntity<ReviewBoardResponse> writeReview(@PathVariable final Long memberId,
                                                           @RequestBody final ReviewBoardRequest reviewBoardRequest) {
        log.info("memberId : {}가 작성한 리뷰 게시글", memberId);
        return ResponseEntity.ok(reviewBoardService.writeReview(memberId, reviewBoardRequest));
    }

    //상태코드 200 300 400 500

    @LoginCheck
    @PatchMapping("/update")
    public ResponseEntity<ReviewBoardResponse> updatePost(@RequestBody final PostUpdateRequest postUpdateRequest) {
        log.info("memberId : {}가 수정한 리뷰 게시글 : {}", postUpdateRequest.getMemberId(), postUpdateRequest.getBoardId());
        return ResponseEntity.ok().body(reviewBoardService.modifyReviewTitleAndContent(postUpdateRequest));
    }

    @LoginCheck
    @PatchMapping("/update-title")
    public ResponseEntity<ReviewBoardResponse> updateTitle(@RequestBody final PostUpdateRequest postUpdateRequest) {
        log.info("memberId : {}가 제목만 수정한 리뷰 게시글 : {}", postUpdateRequest.getMemberId(), postUpdateRequest.getBoardId());
        return ResponseEntity.ok().body(reviewBoardService.modifyReviewTitle(postUpdateRequest));
    }

    @LoginCheck
    @PatchMapping("/update-content")
    public ResponseEntity<ReviewBoardResponse> updateContent(@RequestBody final PostUpdateRequest postUpdateRequest) {
        log.info("memberId : {}가 내용만 수정한 리뷰 게시글 : {}", postUpdateRequest.getMemberId(), postUpdateRequest.getBoardId());
        return ResponseEntity.ok().body(reviewBoardService.modifyReviewContent(postUpdateRequest));
    }

    @LoginCheck
    @DeleteMapping("/delete-post")
    public ResponseEntity<Long> deletePost(@RequestBody final PostDeleteRequest postDeleteRequest) {
        log.info("memberId : {} 가 삭제한 리뷰 게시글 : {}", postDeleteRequest.getMemberId(), postDeleteRequest.getBoardId());
        return ResponseEntity.ok(reviewBoardService.deleteReviewPost(postDeleteRequest));
    }

    @GetMapping("/show-post/{boardId}")
    public ResponseEntity<ReviewBoardResponse> showPost(@PathVariable final Long boardId) {
        log.info("게시글 : {}를 조회한다", boardId);
        return ResponseEntity.ok(reviewBoardService.showPost(boardId));
    }

    @GetMapping("/show-searched-posts")
    public ResponseEntity<List<PerfumeReviewBoard>> showSearchedPosts(@RequestParam final String content) {
        log.info("{} 이 포함된 게시글들을 조회하다.", content);
        return ResponseEntity.ok(reviewBoardService.showSearchedPosts(content));
    }

    @GetMapping("/show-my-posts/{memberId}")
    public ResponseEntity<List<PerfumeReviewBoard>> showMyPosts(@PathVariable final Long memberId) {
        log.info("memberId : {} 가 작성한 리뷰 게시글만 조회한다", memberId);
        return ResponseEntity.ok(reviewBoardService.showMyReviewPost(memberId));
    }

}
