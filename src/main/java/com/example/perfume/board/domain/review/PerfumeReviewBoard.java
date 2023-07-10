package com.example.perfume.board.domain.review;

import com.example.perfume.board.domain.Content;
import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "perfume_review_board")
@EntityListeners(AuditingEntityListener.class)
public class PerfumeReviewBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_board_id", nullable = false)
    private Long boardId;

    @NotNull
    @CreatedDate
    private LocalDateTime createdDateTme;

    @NotNull
    @ManyToOne
    private Member writer;

    @NotNull
    private String title;

    @NotNull
    private Content content;

    @NotNull
    private String perfumeImageUrl;

    @Builder
    public PerfumeReviewBoard(final Long boardId, final Member member, final String title,
                              final Content content, final String perfumeImageUrl) {
        this.boardId = boardId;
        this.writer = member;
        this.content = content;
        this.title = title;
        this.perfumeImageUrl = perfumeImageUrl;
    }

    public void updatePost(String title, Content content){
        this.title = title;
        this.content = content;
    }

}
