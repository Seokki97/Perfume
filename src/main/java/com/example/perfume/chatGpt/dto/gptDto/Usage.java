package com.example.perfume.chatGpt.dto.gptDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Usage {

    @JsonProperty("prompt_tokens")
    private String promptTokens;

    @JsonProperty("completion_tokens")
    private String completionTokens;

    @JsonProperty("total_tokens")
    private String totalTokens;

    public Usage(){
    }

    @Builder
    public Usage(String promptTokens, String completionTokens, String totalTokens) {
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
    }
}
