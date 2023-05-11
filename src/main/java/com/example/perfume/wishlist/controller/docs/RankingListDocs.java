package com.example.perfume.wishlist.controller.docs;

import com.example.perfume.wishlist.dto.RankingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "위시리스트에 담긴 항목들 랭킹 매기는 API")
public interface RankingListDocs {

    @Operation(summary = "위시리스트 항목들의 랭킹을 매겨준다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "랭킹을 산정할 수 없습니다")
    })
    ResponseEntity<List<RankingResponse>> showRanking();
}
