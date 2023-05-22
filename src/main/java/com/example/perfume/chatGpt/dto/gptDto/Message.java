package com.example.perfume.chatGpt.dto.gptDto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Message implements Serializable {

    private String role;

    private String content;

    public Message() {
    }

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
