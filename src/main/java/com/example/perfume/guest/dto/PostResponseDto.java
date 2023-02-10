package com.example.perfume.guest.dto;

import com.example.perfume.guest.domain.Post;
import lombok.Builder;

public class PostResponseDto {
    private Long id;

    private String visitor;

    private String content;


    @Builder
    public PostResponseDto(Long id, String visitor, String content) {
        this.id = id;
        this.visitor = visitor;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .visitor(visitor)
                .content(content)
                .build();
    }
}
