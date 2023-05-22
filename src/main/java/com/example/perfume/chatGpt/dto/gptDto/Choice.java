package com.example.perfume.chatGpt.dto.gptDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Choice implements Serializable {
    private Message message;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;

    public Choice(){}

    @Builder
    public Choice(Message message, Integer index, String finishReason) {

        this.index = index;
        this.finishReason = finishReason;
        this.message = message;
    }
}
