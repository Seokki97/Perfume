package com.example.perfume.perfume.dto.story;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Choice implements Serializable {
    private String text;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;

    public Choice(){}

    @Builder
    public Choice(String text, Integer index, String finishReason) {
        this.text = text;
        this.index = index;
        this.finishReason = finishReason;
    }
}
