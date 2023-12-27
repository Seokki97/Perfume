package com.example.perfume.post.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long postId;
    private String visitor;
    private String content;

    public PostResponseDto() {

    }

    public PostResponseDto(Long postId, String visitor, String content) {
        this.postId = postId;
        this.visitor = visitor;
        this.content = content;
    }
}
