package com.example.perfume.board.controller;

import com.example.perfume.board.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.board.dto.responseDto.ReviewBoardResponse;
import com.example.perfume.board.service.ReviewBoardService;
import com.example.perfume.member.service.jwt.LoginCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class ReviewBoardController {

    private final ReviewBoardService reviewBoardService;

    public ReviewBoardController(ReviewBoardService reviewBoardService) {
        this.reviewBoardService = reviewBoardService;
    }

    @LoginCheck
    @PostMapping("/write/{memberId}")
    public ResponseEntity<ReviewBoardResponse> writeReview(@PathVariable final Long memberId, ReviewBoardRequest reviewBoardRequest) {

        return ResponseEntity.ok(reviewBoardService.writeReview(memberId, reviewBoardRequest));
    }
}
