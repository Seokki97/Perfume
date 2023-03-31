package com.example.perfume.feedback.controller;

import com.example.perfume.feedback.dto.FeedbackRequestDto;
import com.example.perfume.feedback.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService = feedbackService;
    }
    @PostMapping("/save")
    public void saveFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto){
        feedbackService.saveFeedback(feedbackRequestDto);
    }

}
