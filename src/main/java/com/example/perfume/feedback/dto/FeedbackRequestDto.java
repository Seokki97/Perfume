package com.example.perfume.feedback.dto;

import com.example.perfume.feedback.domain.Feedback;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedbackRequestDto {

    private String serviceName;
    private String comment;

    public FeedbackRequestDto() {

    }

    @Builder
    public FeedbackRequestDto(String serviceName, String comment) {
        this.serviceName = serviceName;
        this.comment = comment;
    }

    public Feedback toEntity() {
        return new Feedback(serviceName, comment);
    }
}
