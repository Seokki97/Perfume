package com.example.perfume.post.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;

    private String visitor;

    private String content;


    public PostResponseDto() {

    }

    public PostResponseDto(Long id, String visitor, String content) {
        this.id = id;
        this.visitor = visitor;
        this.content = content;
    }
}
