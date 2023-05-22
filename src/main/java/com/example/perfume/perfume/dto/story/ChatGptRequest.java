package com.example.perfume.perfume.dto.story;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.Arrays;

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
