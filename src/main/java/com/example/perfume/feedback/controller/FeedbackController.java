package com.example.perfume.feedback.controller;

import com.example.perfume.feedback.dto.FeedbackRequestDto;
import com.example.perfume.feedback.service.FeedbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService = feedbackService;
    }
    @GetMapping("/save")
    public void saveFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto){
        feedbackService.saveFeedback(feedbackRequestDto);
    }
}
