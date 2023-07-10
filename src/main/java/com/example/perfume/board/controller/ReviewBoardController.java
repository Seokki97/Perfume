package com.example.perfume.board.controller;

import com.example.perfume.board.dto.requestDto.PostUpdateRequest;
import com.example.perfume.board.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.board.dto.responseDto.ReviewBoardResponse;
import com.example.perfume.board.service.ReviewBoardService;
import com.example.perfume.member.service.jwt.LoginCheck;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
