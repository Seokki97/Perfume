package com.example.perfume.feedback.controller;

import com.example.perfume.feedback.controller.docs.FeedbackControllerDocs;
import com.example.perfume.feedback.dto.FeedbackRequestDto;
import com.example.perfume.feedback.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/feedback")
public class FeedbackController implements FeedbackControllerDocs {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto) {
        feedbackService.saveFeedback(feedbackRequestDto);
        log.info("피드백 저장 요청");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/file")
    public ResponseEntity<Void> saveFile() throws IOException {
        feedbackService.makeFeedbackFile();
        return ResponseEntity.noContent().build();
    }

}
