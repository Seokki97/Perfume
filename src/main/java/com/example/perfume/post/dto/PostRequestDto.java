package com.example.perfume.post.dto;

import com.example.perfume.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostRequestDto {

    private Long id;

    private String visitor;

    private String content;

    public PostRequestDto() {

    }

    @Builder
    public PostRequestDto(Long id, String visitor, String content) {
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
