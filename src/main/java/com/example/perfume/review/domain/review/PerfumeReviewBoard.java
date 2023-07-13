package com.example.perfume.review.domain.review;

import com.example.perfume.review.domain.Content;
import com.example.perfume.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private Long likeCount;

    @NotNull
    private Long unlikeCount;

    @Builder
    public PerfumeReviewBoard(final Long boardId, final Member member, final String title,
                              final Content content) {
        this.boardId = boardId;
        this.writer = member;
        this.content = content;
        this.title = title;
        this.likeCount = 0L;
    }

    public void updatePost(String title, Content content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLikeCount() {
        this.likeCount += 1;
    }

    public void decreaseLikeCount() {
        this.likeCount -= 1;
    }

    public void increaseUnlikeCount(){
        this.unlikeCount += 1;
    }
    public void decreaseUnlikeCount(){
        this.likeCount -= 1;
    }

}
