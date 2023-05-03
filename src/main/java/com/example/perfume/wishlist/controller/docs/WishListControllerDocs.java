package com.example.perfume.wishlist.controller.docs;

import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.dto.WishListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "WishList API",description = "여기에 들어가는 memberId는 전부 PK 값으로 부탁해용>_< (MemberEntity의 memberId 말고 id필드 값 말하는겨)")
public interface WishListControllerDocs {

    @Operation(summary = "향수 위시리스트에 담기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "위시리스트가 비어있음"),
            @ApiResponse(responseCode = "409", description = "위시리스트에 중복된 항목을 담으려함"),
            @ApiResponse(responseCode = "429", description = "15개가 넘는 항목을 WishList에 담으려함"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "Access Token"))
    })
    ResponseEntity<WishListResponse> selectLikePerfume(@Parameter(name = "WishListRequestDto", description = "memberId,PerfumeId, memberId는 PK값") @RequestBody WishListRequest wishListRequest);

    @Operation(summary = "위시리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "위시리스트가 비어있음"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "Access Token"))
    })
    ResponseEntity<List<WishList>> showWishList(@Parameter(name = "memberId", description = "Pk값으로 주세용") @PathVariable("memberId") Long memberId);

    @Operation(summary = "모든 위시리스트 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "위시리스트가 비어있음"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "Access Token"))
    })
    ResponseEntity<Void> deleteAllWishedPerfume(@Parameter(name = "memberId", description = "Pk값으로 주세용") @PathVariable("memberId") Long memberId);

    @Operation(summary = "선택한 위시리스트 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "위시리스트가 비어있음"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근",
                    headers = @Header(name = "Authorization", description = "Access Token"))
    })
    ResponseEntity<Void> deleteSelectedPerfume(@Parameter(name = "WishListRequestDto", description = "memberId,PerfumeId, memberId는 PK값") @RequestBody WishListRequest wishListRequest);
}
