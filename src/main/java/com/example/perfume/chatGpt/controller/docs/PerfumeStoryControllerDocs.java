package com.example.perfume.chatGpt.controller.docs;

import com.example.perfume.chatGpt.dto.gptDto.ChatGptResponse;
import com.example.perfume.chatGpt.dto.storyDto.PerfumeStoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Gpt 향수 스토리 만들기 Api")
public interface PerfumeStoryControllerDocs {

    @Operation(summary = "향수 이야기 만들기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "이야기를 생성할 수 없음")
    })
    ResponseEntity<ChatGptResponse> makePerfumeStory(@Parameter(description = "이름, 향, 무드, 계절, 옷스타일") @RequestBody PerfumeStoryRequest perfumeStoryRequest);
}
