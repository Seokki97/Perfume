package com.example.perfume.perfume.dto.story;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ChatGptRequest implements Serializable {

    private String model;
    private String prompt;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;

    public ChatGptRequest(){}
    @Builder
    public ChatGptRequest(String model, String prompt, Integer maxTokens, Double temperature, Double topP) {
        this.model = model;
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }
}
