package com.example.perfume.feedback.controller;

import com.example.perfume.feedback.dto.FeedbackRequestDto;
import com.example.perfume.feedback.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto) {
        feedbackService.saveFeedback(feedbackRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/file")
    public ResponseEntity<Void> saveFile() throws IOException {
        feedbackService.makeFeedbackFile();
        return ResponseEntity.noContent().build();
    }

}
