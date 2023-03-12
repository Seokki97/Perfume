package com.example.perfume.survey.domain.post.dto;

import com.example.perfume.survey.domain.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;

    private String visitor;

    private String content;


    public PostResponseDto() {

    }
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
