package com.example.perfume.review.controller.docs;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "향수 리뷰 게시판 Api")
public interface ReviewLikeControllerDocs {
    @Operation(summary = "리뷰에 좋아요 버튼 클릭")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "Access Token"))
    })
    ResponseEntity<Void> likePost(@Parameter(name = "memberId, boardId") @RequestBody ReviewLikeRequest reviewLikeRequest);

    @Operation(summary = "리뷰에 싫어요 버튼 클릭")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "Access Token"))
    })
    ResponseEntity<Void> unlikePost(@Parameter(name = "memberId, boardId") @RequestBody ReviewLikeRequest reviewLikeRequest);

}
