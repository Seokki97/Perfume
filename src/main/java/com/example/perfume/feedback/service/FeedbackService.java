package com.example.perfume.feedback.service;

import com.example.perfume.feedback.domain.Feedback;
import com.example.perfume.feedback.dto.FeedbackRequestDto;
import com.example.perfume.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class FeedbackService {

    private static final String filePath = "C:/Users/wnstj/perfume/feedback.txt";
    //private static final String filePath = "/home/ubuntu/data/feedback.txt";
    private final BufferedWriter bufferedWriter;

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) throws IOException {
        this.feedbackRepository = feedbackRepository;
        File importedFile = new File(filePath);
        this.bufferedWriter = new BufferedWriter(new FileWriter(importedFile));
    }

    public void saveFeedback(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = feedbackRequestDto.toEntity();

        feedbackRepository.save(feedback);
    }

    public void makeFeedbackFile() throws IOException {
        List<Feedback> feedbackList = feedbackRepository.findAll();

        for (Feedback feedback : feedbackList) {
            bufferedWriter.write(String.valueOf(feedback.getComment()));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

}


