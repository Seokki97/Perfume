package com.example.perfume.survey.controller.docs;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "향수 설문 API")
public interface SurveyControllerDocs {

    @Operation(summary = "설문에 따른 향수 추천")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "설문 결과 찾을 수 없음")
    })
    ResponseEntity<List<Perfume>> showPerfumeDataBySurvey(@Parameter(description = "성별, 향, 무드, 계절, 옷스타일") @RequestBody SurveyRequestDto surveyRequestDto);
}
