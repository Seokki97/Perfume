package com.example.perfume.review;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.ReviewAnalyzeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewAnalyzeServiceTest {

    @InjectMocks
    private ReviewAnalyzeService reviewAnalyzeService;

    @Mock
    private ReviewBoardRepository reviewBoardRepository;

    @DisplayName("게시글 리뷰 많은 순으로 정렬한다.")
    @Test
    void sortLikeReview() {

        PerfumeReviewBoard expectedCaseOne = PerfumeReviewBoard.builder()
                .boardId(1L)
                .build();
        for (int i = 0; i < 10; i++) {
            expectedCaseOne.increaseLikeCount();
        }

        PerfumeReviewBoard expectedCaseTwo = PerfumeReviewBoard.builder()
                .boardId(2L)
                .build();
        for (int i = 0; i < 4; i++) {
            expectedCaseTwo.increaseLikeCount();
        }

        List<PerfumeReviewBoard> mockReviewBoard = new ArrayList<>();
        mockReviewBoard.add(expectedCaseOne);
        mockReviewBoard.add(expectedCaseTwo);

        when(reviewBoardRepository.findAllByLikeCount(Sort.Direction.DESC,"likeCount")).thenReturn(mockReviewBoard);

        List<PerfumeReviewBoard> result = reviewAnalyzeService.sortByMostLikeReviews();

        Assertions.assertAll(
                () -> Assertions.assertEquals(result.get(0).getBoardId(), expectedCaseOne.getBoardId()),
                () -> Assertions.assertEquals(result.get(1).getBoardId(), expectedCaseTwo.getBoardId())
        );
    }

    @DisplayName("게시글 unlike가 많은 순으로 정렬한다.")
    @Test
    void sortUnLikeReview() {

        PerfumeReviewBoard expectedCaseOne = PerfumeReviewBoard.builder()
                .boardId(1L)
                .build();
        for (int i = 0; i < 10; i++) {
            expectedCaseOne.increaseUnlikeCount();
        }

        PerfumeReviewBoard expectedCaseTwo = PerfumeReviewBoard.builder()
                .boardId(2L)
                .build();
        for (int i = 0; i < 4; i++) {
            expectedCaseTwo.increaseUnlikeCount();
        }

        List<PerfumeReviewBoard> mockReviewBoard = new ArrayList<>();
        mockReviewBoard.add(expectedCaseOne);
        mockReviewBoard.add(expectedCaseTwo);

        when(reviewBoardRepository.findAllByLikeCount(Sort.Direction.DESC,"unlikeCount")).thenReturn(mockReviewBoard);

        List<PerfumeReviewBoard> result = reviewAnalyzeService.sortByMostUnlikeReviews();

        Assertions.assertAll(
                () -> Assertions.assertEquals(result.get(0).getBoardId(), expectedCaseOne.getBoardId()),
                () -> Assertions.assertEquals(result.get(1).getBoardId(), expectedCaseTwo.getBoardId())
        );
    }

}
