package com.example.perfume.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

    private String content;

    public PostRequestDto() {

    }

    public PostRequestDto(String content) {
        this.content = content;
    }
}
