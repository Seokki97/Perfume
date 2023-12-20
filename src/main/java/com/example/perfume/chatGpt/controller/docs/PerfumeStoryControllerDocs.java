package com.example.perfume.chatGpt.controller.docs;

import com.example.perfume.chatGpt.dto.gptDto.ChatGptResponse;
import com.example.perfume.chatGpt.dto.storyDto.PerfumeStoryRequest;
import com.example.perfume.common.APICommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Gpt 향수 스토리 만들기 Api")
public interface PerfumeStoryControllerDocs {

    @Operation(summary = "향수 이야기 만들기")
    @APICommonResponse
    ResponseEntity<ChatGptResponse> makePerfumeStory(
            @Parameter(description = "이름, 향, 무드, 계절, 옷스타일") @RequestBody PerfumeStoryRequest perfumeStoryRequest);
}
