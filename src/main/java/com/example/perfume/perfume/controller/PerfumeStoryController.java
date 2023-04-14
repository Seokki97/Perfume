package com.example.perfume.perfume.controller;

import com.example.perfume.perfume.dto.story.ChatGptResponse;
import com.example.perfume.perfume.dto.story.PerfumeStoryRequest;
import com.example.perfume.perfume.service.PerfumeStoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfume")
public class PerfumeStoryController {
    private final PerfumeStoryService perfumeStoryService;

    public PerfumeStoryController(PerfumeStoryService perfumeStoryService) {
        this.perfumeStoryService = perfumeStoryService;
    }

    @PostMapping("/make-story")
    public ResponseEntity<ChatGptResponse> makePerfumeStory(@RequestBody PerfumeStoryRequest perfumeStoryRequest) {

        return ResponseEntity.ok(perfumeStoryService.askQuestionToChatGpt(perfumeStoryRequest));
    }
}
