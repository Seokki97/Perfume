package com.example.perfume.recommend.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Tag(name = "타인이 추천해준 향수 분석 결과 API")
public interface AnalyzeControllerDocs {

    @Operation(summary = "가장 많이 추천받은 향수, 향기 제공")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "가장 많이 추천받은 향 조회 불가")
    })
    ResponseEntity<Map<String, Long>> showMostRecommendedPerfume(@Parameter(description = "향수 id") @PathVariable("id") Long memberId);
}
