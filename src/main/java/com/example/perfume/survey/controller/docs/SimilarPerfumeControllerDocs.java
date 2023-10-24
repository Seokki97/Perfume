package com.example.perfume.survey.controller.docs;

import com.example.perfume.perfume.domain.Perfume;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "유사한 향수 추천 API")
public interface SimilarPerfumeControllerDocs {

    @Operation(summary = "유사한 향수 추천")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "유사한 향수 없음")
    })
    ResponseEntity<List<Perfume>> showSimilarData(@Parameter(description = "향수 id") @PathVariable("id") Long id);
}
