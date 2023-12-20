package com.example.perfume.survey.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "향수 설문 API")
public interface SurveyControllerDocs {

    @Operation(summary = "설문에 따른 향수 추천")
    @APICommonResponse
    ResponseEntity<List<Perfume>> showPerfumeDataBySurvey(
            @Parameter(description = "성별, 향, 무드, 계절, 옷스타일") @RequestBody SurveyRequestDto surveyRequestDto);
}
