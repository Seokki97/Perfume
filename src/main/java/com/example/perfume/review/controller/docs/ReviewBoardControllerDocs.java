package com.example.perfume.review.controller.docs;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.requestDto.PostDeleteRequest;
import com.example.perfume.review.dto.requestDto.PostUpdateRequest;
import com.example.perfume.review.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.responseDto.ReviewBoardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "향수 리뷰 게시판 Api")
public interface ReviewBoardControllerDocs {

    @Operation(summary = "리뷰 게시글 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "Access Token"))
    })
    ReviewBoardResponse writeReview(@Parameter(name = "memberId", description = "Member PK값") @PathVariable Long memberId,
                                    @Parameter(name = "BoardRequest", description = "") @RequestBody ReviewBoardRequest boardRequest);

    @Operation(summary = "리뷰 게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "AccessToken"))
    })
    ReviewBoardResponse modifyReview(@Parameter(name = "PostUpdateRequest", description = "수정된 글 내용") @RequestBody PostUpdateRequest postUpdateRequest);

    @Operation(summary = "리뷰 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "AccessToken"))
    })
    Long deletePost(@Parameter(name = "PostDeleteRequest", description = "memberId, boardId") @RequestBody PostDeleteRequest postDeleteRequest);

    @Operation(summary = "게시글 단일 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "해당 게시글을 찾을 수 없음")
    })
    ResponseEntity<ReviewBoardResponse> showPost(@Parameter(name = "boardId", description = "게시글 번호") @PathVariable final Long boardId);

    @Operation(summary = "게시글 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(description = "빈 객체 응답 시 -> 게시글 찾을 수 없음")
    })
    ResponseEntity<PerfumeReviewBoard> showSearchedPosts(@Parameter(name = "content", description = "내용") @RequestParam String content);
}
