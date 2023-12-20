package com.example.perfume.recommend.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.recommend.dto.analyze.RankingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "타인이 추천해준 향수 분석 결과 API")
public interface AnalyzeControllerDocs {

    @Operation(summary = "가장 많이 추천받은 향수, 향기 제공")
    @APICommonResponse
    ResponseEntity<RankingResponse> showRankingList(
            @Parameter(description = "향수 id") @PathVariable("id") Long memberId);
}
