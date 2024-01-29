package com.example.perfume.review.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.responseDto.ReviewAnalyzeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "향수 리뷰 게시판 Api")
public interface ReviewAnalyzeControllerDocs {

    @Operation(summary = "리뷰 좋아요 많은 순 정렬")
    @APICommonResponse
    ResponseEntity<ReviewAnalyzeResponse> showMostLikedPerfume();

    @Operation(summary = "리뷰 좋아요 적은 순 정렬")
    @APICommonResponse
    ResponseEntity<List<PerfumeReviewBoard>> showMostUnlikedPerfume(String perfumeName);
}
