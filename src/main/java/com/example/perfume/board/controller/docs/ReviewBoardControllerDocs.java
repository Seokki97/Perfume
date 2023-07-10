package com.example.perfume.board.controller.docs;

import com.example.perfume.board.dto.requestDto.PostUpdateRequest;
import com.example.perfume.board.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.board.dto.responseDto.ReviewBoardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    ReviewBoardResponse modifyReview(@Parameter(name = "PostUpdateRequest", description = "수정된 글 내용") PostUpdateRequest postUpdateRequest);

}
