package com.example.perfume.review.dto.review.requestDto;

import com.example.perfume.review.domain.Content;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewBoardRequest {

    private Long boardId;

    private Long writer;

    private String title;

    private Content content;

    private String perfumeName;


    @Builder
    public ReviewBoardRequest(final Long boardId, final Long writer, final String title,
                              final Content content, final String perfumeName) {
        this.boardId = boardId;
        this.writer = writer;
        this.content = content;
        this.title = title;
        this.perfumeName = perfumeName;
    }

    public PerfumeReviewBoard toEntity(Member member, Content content) {
        return PerfumeReviewBoard.builder()
                .member(member)
                .content(content)
                .title(title)
                .build();
    }
}