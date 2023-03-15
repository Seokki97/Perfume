package com.example.perfume.feedback.service;

import com.example.perfume.feedback.domain.Feedback;
import com.example.perfume.feedback.dto.FeedbackRequestDto;
import com.example.perfume.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void saveFeedback(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = feedbackRequestDto.toEntity();

        feedbackRepository.save(feedback);
    }

}
