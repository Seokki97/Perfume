package com.example.perfume.board.dto.requestDto;

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
