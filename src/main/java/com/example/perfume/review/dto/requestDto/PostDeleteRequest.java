package com.example.perfume.review.dto.requestDto;

import lombok.Getter;

@Getter
public class PostDeleteRequest {

    private Long memberId;

    private Long boardId;

    public PostDeleteRequest(Long memberId, Long boardId){
        this.memberId = memberId;
        this.boardId = boardId;
    }
}
