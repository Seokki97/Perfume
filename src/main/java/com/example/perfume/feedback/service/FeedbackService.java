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

    private final FeedbackRepository feedbackRepository;

    private static final String filePath = "C:/Users/wnstj/perfume/feedback.txt";

    private final File importedFile;
    private final BufferedWriter bufferedWriter;

    public FeedbackService(FeedbackRepository feedbackRepository) throws IOException {
        this.feedbackRepository = feedbackRepository;
        this.importedFile = new File(filePath);
        this.bufferedWriter = new BufferedWriter(new FileWriter(importedFile));
    }

    public void saveFeedback(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = feedbackRequestDto.toEntity();

        feedbackRepository.save(feedback);
    }

    public void makeFeedbackFile() throws IOException {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        int listSize = feedbackList.size();
        for (int i = 0; i < listSize; i++) {
            bufferedWriter.write(String.valueOf(feedbackList.get(i).getComment()));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferClose();
    }

    public void bufferClose() throws IOException {
        bufferedWriter.close();
    }
}


