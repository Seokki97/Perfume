package com.example.perfume.review;

import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.ReviewAnalyzeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewAnalyzeServiceTest {

    @InjectMocks
    private ReviewAnalyzeService reviewAnalyzeService;

    @Mock
    private ReviewBoardRepository reviewBoardRepository;

    @DisplayName("게시글 리뷰 많은 순으로 정렬한다.")
    @Test
    void sortLikeReview() {
    }

    @DisplayName("게시글 unlike가 많은 순으로 정렬한다.")
    @Test
    void sortUnLikeReview() {

    }

}
