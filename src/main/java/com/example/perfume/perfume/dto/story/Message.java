package com.example.perfume.perfume.dto.story;

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
