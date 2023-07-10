package com.example.perfume.board.dto.requestDto;

import com.example.perfume.board.domain.Content;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequest {

    private Long boardId;

    private Long memberId;

    private String title;

    private Content content;

    @Builder
    public PostUpdateRequest(Long boardId, Long memberId, String title, Content content) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
}
