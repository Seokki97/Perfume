package com.example.perfume.survey.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.perfume.domain.Perfume;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "유사한 향수 추천 API")
public interface SimilarPerfumeControllerDocs {

    @Operation(summary = "유사한 향수 추천")
    @APICommonResponse
    ResponseEntity<List<Perfume>> showSimilarData(@Parameter(description = "향수 id") @PathVariable("id") Long id);
}
