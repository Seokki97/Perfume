package com.example.perfume.board.dto.requestDto;

import com.example.perfume.board.domain.Content;
import com.example.perfume.board.domain.review.PerfumeReviewBoard;
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

    public PerfumeReviewBoard toEntity(Member member, Content content,Perfume perfume) {
        return PerfumeReviewBoard.builder()
                .member(member)
                .content(content)
                .perfumeImageUrl(perfume.getPerfumeImageUrl())
                .title(title)
                .build();
    }
}
