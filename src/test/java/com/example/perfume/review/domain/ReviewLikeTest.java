package com.example.perfume.review.domain;

import static org.mockito.ArgumentMatchers.any;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.exception.AlreadyPushLikeException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.repository.ReviewLikeRepository;
import com.example.perfume.review.service.ReviewLikeService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewLikeTest {

    @Mock
    private ReviewLikeRepository reviewLikeRepository;
    @Mock
    private ReviewBoardRepository reviewBoardRepository;
    @InjectMocks
    private ReviewLikeService reviewLikeService;


    @DisplayName("이미 푸시버튼을 누른 경우 예외를 발생시킨다")
    @Test
    void validateAlreadyPush() {

        ReviewLikeRequest reviewLikeRequest = new ReviewLikeRequest(1l, 1l, null);
        PerfumeReviewBoard perfumeReviewBoard = PerfumeReviewBoard.builder().boardId(1L).build();
        Mockito.when(reviewBoardRepository.findByBoardId(any())).thenReturn(Optional.ofNullable(perfumeReviewBoard));
        Mockito.when(reviewLikeRepository.existsReviewLikeByLikedPost(any())).thenReturn(true);

        Assertions.assertAll(() -> Assertions.assertThrows(AlreadyPushLikeException.class,
                () -> reviewLikeService.validateAlreadyPushLike(reviewLikeRequest)));

    }

    @DisplayName("게시글을 Like상태로 전환한다. 이미 Like상태일 경우 Canceled상태로 전환한다.")
    @Test
    void updateLike() {
    }

    @DisplayName("게시글을 Unlike상태로 전환한다. 이미 Unlike 상태일 경우 Canceled상태로 전환한다")
    @Test
    void updateUnlike() {

    }

}
