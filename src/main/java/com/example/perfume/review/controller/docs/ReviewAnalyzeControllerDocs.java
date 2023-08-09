package com.example.perfume.review.controller.docs;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "향수 리뷰 게시판 Api")
public interface ReviewAnalyzeControllerDocs {

    @Operation(summary = "리뷰 좋아요 많은 순 정렬")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "200", description = " 빈 객체 들어올 시 데이터가 없음")
    })
    ResponseEntity<List<PerfumeReviewBoard>> showMostLikedPerfume(String perfumeName);

    @Operation(summary = "리뷰 좋아요 적은 순 정렬")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "200", description = " 빈 객체 들어올 시 데이터가 없음")
    })
    ResponseEntity<List<PerfumeReviewBoard>> showMostUnlikedPerfume(String perfumeName);
}
