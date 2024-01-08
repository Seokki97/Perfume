package com.example.perfume.review.dto.review.requestDto;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.Content;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewBoardRequest {


    private Long writer;

    private String title;

    private Content content;

    private String perfumeName;

    public ReviewBoardRequest() {
    }

    @Builder
    public ReviewBoardRequest(final Long writer, final String title,
                              final Content content, final String perfumeName) {
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
