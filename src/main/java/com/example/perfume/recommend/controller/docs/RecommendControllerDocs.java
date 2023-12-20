package com.example.perfume.recommend.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.RecommendRequestDto;
import com.example.perfume.recommend.dto.RecommendResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "타인 향수 추천 API")
public interface RecommendControllerDocs {

    @Operation(summary = "타인 향수 추천 api")
    @APICommonResponse
    ResponseEntity<Recommendation> recommendPerfume(
            @Parameter(description = "RecommendRequest 데이터") @RequestBody RecommendRequestDto recommendRequestDto);

    @Operation(summary = "타인이 추천해준 향수 조회하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", headers = @Header(name = "Authorization", description = "Access Token")),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자의 접근")
    })
    ResponseEntity<RecommendResponseDto> showRecommendedPerfume(
            @Parameter(description = "회원 id") @PathVariable("id") Long id, HttpServletRequest httpServletRequest);
}
