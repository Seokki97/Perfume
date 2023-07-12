package com.example.perfume.review.dto.review.responseDto;

import com.example.perfume.review.domain.Content;
import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewBoardResponse {

    private Long boardId;

    private Member writer;

    private String title;

    private Content content;

    private Perfume perfume;


    @Builder
    public ReviewBoardResponse(final Long boardId, final Member member, final String title,
                              final Content content, final Perfume perfume) {
        this.boardId = boardId;
        this.writer = member;
        this.content = content;
        this.title = title;
        this.perfume = perfume;
    }
}