package com.example.perfume.review.domain.review;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.Content;
import com.example.perfume.review.exception.ReviewTitleDuplicatedException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "perfume_review_board")
@EntityListeners(AuditingEntityListener.class)
public class PerfumeReviewBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @NotNull
    @CreatedDate
    private LocalDateTime createdDateTme;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @NotNull
    private String title;

    @NotNull
    private Content content;

    @Embedded
    private LikeCount likeCount;

    @Version
    private Long version;

    @Builder
    public PerfumeReviewBoard(final Long boardId, final Member member, final String title, final Content content,
                              final LikeCount likeCount) {
        this.boardId = boardId;
        this.writer = member;
        this.content = content;
        this.title = title;
        this.likeCount = likeCount;
    }

    public void updatePost(String title, Content content) {
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(Content content) {
        this.content = content;
    }

    public void validatePostDuplication(ReviewBoardRepository reviewBoardRepository) {
        if (reviewBoardRepository.existsByTitle(title)) {
            throw new ReviewTitleDuplicatedException();
        }
    }
}

