package com.example.perfume.wishlist.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.wishlist.dto.RankingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "위시리스트에 담긴 항목들 랭킹 매기는 API")
public interface RankingListDocs {

    @Operation(summary = "위시리스트 항목들의 랭킹을 매겨준다.")
    @APICommonResponse
    ResponseEntity<List<RankingResponse>> showRanking();
}
