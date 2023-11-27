package com.example.perfume.review.domain;

import static org.mockito.ArgumentMatchers.any;

import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.exception.ReviewTitleDuplicatedException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PerfumeReviewBoardTest {

    @Mock
    private ReviewBoardRepository reviewBoardRepository;

    @DisplayName("사용자가 좋아요를 누른다. 상태에따라 Count를 다르게 업데이트 한다.")
    @Test
    void updateLike() {
        PerfumeReviewBoard perfumeReviewBoard = PerfumeReviewBoard.builder()
                .build();

        ReviewLike reviewLike = ReviewLike.builder()
                .likeStatus(LikeStatus.UNLIKE)
                .build();

        //likeStatus가 Unlike일 경우
        //likeStatus가 Cenceled일 경우
        //likeStatus가 Like일 경우
        perfumeReviewBoard.likePost(reviewLike);
        Assertions.assertAll(
                () -> Assertions.assertEquals(-1L, perfumeReviewBoard.getLikeCount()),
                () -> Assertions.assertEquals(1L, perfumeReviewBoard.getUnlikeCount())
        );
    }

    @DisplayName("게시글 제목이 중복되었는지 검증한다. 중복되었을 경우 ReviewTitleDuplicatedException을 발생시킨다.")
    @Test
    void validateTitleIsDuplicate() {
        PerfumeReviewBoard perfumeReviewBoard = PerfumeReviewBoard.builder()
                .boardId(1l)
                .title("hi")
                .build();

        Mockito.when(reviewBoardRepository.existsByTitle(any())).thenReturn(true);

        Assertions.assertAll(
                () -> Assertions.assertThrows(ReviewTitleDuplicatedException.class,
                        () -> perfumeReviewBoard.validatePostDuplication(reviewBoardRepository))
        );
    }

}
