package com.example.perfume.review.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.requestDto.PostDeleteRequest;
import com.example.perfume.review.dto.review.requestDto.PostUpdateRequest;
import com.example.perfume.review.dto.review.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.review.responseDto.ReviewBoardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
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
    ResponseEntity<ReviewBoardResponse> writeReview(
            @Parameter(name = "memberId", description = "Member PK값") @PathVariable Long memberId,
            @Parameter(name = "BoardRequest", description = "") @RequestBody ReviewBoardRequest boardRequest);

    @Operation(summary = "리뷰 게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "AccessToken"))
    })
    ResponseEntity<ReviewBoardResponse> updatePost(
            @Parameter(name = "PostUpdateRequest", description = "수정된 글 내용") @RequestBody PostUpdateRequest postUpdateRequest);

    @Operation(summary = "리뷰 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "AccessToken"))
    })
    ResponseEntity<Long> deletePost(
            @Parameter(name = "PostDeleteRequest", description = "memberId, boardId") @RequestBody PostDeleteRequest postDeleteRequest);

    @Operation(summary = "게시글 단일 조회")
    @APICommonResponse
    ResponseEntity<ReviewBoardResponse> showPost(
            @Parameter(name = "boardId", description = "게시글 번호") @PathVariable final Long boardId);

    @Operation(summary = "게시글 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(description = "빈 객체 응답 시 -> 게시글 찾을 수 없음")
    })
    ResponseEntity<List<PerfumeReviewBoard>> showSearchedPosts(
            @Parameter(name = "content", description = "내용") @RequestParam String content);

    @Operation(summary = "내 리뷰글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "200", description = "빈 리스트 들어올 시 없음"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "AccessToken"))
    })
    ResponseEntity<List<PerfumeReviewBoard>> showMyPosts(
            @Parameter(name = "memberId") @PathVariable final Long memberId);

    @Operation(summary = "리뷰 제목 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "AccessToken"))
    })
    ResponseEntity<ReviewBoardResponse> updateTitle(
            @Parameter(name = " ReviewBoardRequest") @RequestBody final PostUpdateRequest postUpdateRequest);


    @Operation(summary = "리뷰 제목 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "AccessToken"))
    })
    ResponseEntity<ReviewBoardResponse> updateContent(
            @Parameter(name = " ReviewBoardRequest") @RequestBody final PostUpdateRequest postUpdateRequest);
}
