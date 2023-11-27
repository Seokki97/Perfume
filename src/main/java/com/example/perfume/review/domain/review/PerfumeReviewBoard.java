package com.example.perfume.review.domain.review;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.Content;
import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.exception.ReviewTitleDuplicatedException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    public PerfumeReviewBoard(final Long boardId, final Member member, final String title, final Content content) {
        this.boardId = boardId;
        this.writer = member;
        this.content = content;
        this.title = title;
        this.likeCount = 0L;
        this.unlikeCount = 0L;
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

    public void increaseLikeCount() {
        this.likeCount += 1;
    }

    public void decreaseLikeCount() {
        this.likeCount -= 1;
    }

    public void increaseUnlikeCount() {
        this.unlikeCount += 1;
    }

    public void decreaseUnlikeCount() {
        this.unlikeCount -= 1;
    }

    public void likePost(ReviewLike like) {
        if (like.getLikeStatus() == LikeStatus.UNLIKE) {
            decreaseLikeCount();
            increaseUnlikeCount();
        }
        if (like.getLikeStatus() == LikeStatus.CANCELED) {
            increaseLikeCount();
        }
        if (like.getLikeStatus() == LikeStatus.LIKE) {
            decreaseLikeCount();
        }
    }

    public void unlikePost(ReviewLike like) {
        if (like.getLikeStatus() == LikeStatus.LIKE) {
            decreaseLikeCount();
            increaseUnlikeCount();
        }
        if (like.getLikeStatus() == LikeStatus.CANCELED) {
            increaseUnlikeCount();
        }
        if (like.getLikeStatus() == LikeStatus.LIKE) {
            decreaseUnlikeCount();
        }
    }

    public void validatePostDuplication(ReviewBoardRepository reviewBoardRepository) {
        if (reviewBoardRepository.existsByTitle(title)) {
            throw new ReviewTitleDuplicatedException();
        }
    }
}

