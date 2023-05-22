package com.example.perfume.chatGpt.dto.gptDto;

import lombok.Builder;
import lombok.Getter;
import org.json.simple.JSONArray;

import java.io.Serializable;

@Getter
public class ChatGptRequest implements Serializable {

    private String model;

    private JSONArray messages;

    public ChatGptRequest() {
    }

    @Builder
    public ChatGptRequest(String model, JSONArray messages) {
        this.model = model;
        this.messages = messages;
    }
}
