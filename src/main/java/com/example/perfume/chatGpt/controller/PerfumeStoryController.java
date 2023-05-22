package com.example.perfume.chatGpt.controller;

import com.example.perfume.chatGpt.controller.docs.PerfumeStoryControllerDocs;
import com.example.perfume.chatGpt.dto.gptDto.ChatGptResponse;
import com.example.perfume.chatGpt.dto.storyDto.PerfumeStoryRequest;
import com.example.perfume.chatGpt.service.PerfumeStoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/perfume")
public class PerfumeStoryController implements PerfumeStoryControllerDocs {
    private final PerfumeStoryService perfumeStoryService;

    public PerfumeStoryController(PerfumeStoryService perfumeStoryService) {
        this.perfumeStoryService = perfumeStoryService;
    }

    @PostMapping("/make-story")
    public ResponseEntity<ChatGptResponse> makePerfumeStory(@RequestBody final PerfumeStoryRequest perfumeStoryRequest) {
        log.info("Chat GPT에게 향수 스토리 생성 요청, 질문 내용 : {}",perfumeStoryRequest.toPromptString());
        return ResponseEntity.ok(perfumeStoryService.askQuestionToChatGpt(perfumeStoryRequest));
    }

}
