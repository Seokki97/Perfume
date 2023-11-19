package com.example.perfume.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

    private Long id;

    private String visitor;

    private String content;

    public PostRequestDto() {

    }

    public PostRequestDto(Long id, String visitor, String content) {
        this.id = id;
        this.visitor = visitor;
        this.content = content;
    }
}
