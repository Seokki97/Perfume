package com.example.perfume.feedback.controller.docs;

import com.example.perfume.feedback.dto.FeedbackRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "피드백 API")
public interface FeedbackControllerDocs {

    @Operation(summary = "피드백 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "실패")
    })
    ResponseEntity<Void> saveFeedback(
            @Parameter(name = "serviceName, comment") @RequestBody FeedbackRequestDto feedbackRequestDto);
}
