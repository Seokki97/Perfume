package com.example.perfume.review.domain;

import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PerfumeReviewBoardTest {

    @DisplayName("사용자가 좋아요를 누른다. 상태에따라 Count를 다르게 업데이트 한다.")
    @Test
    void updateLike(){
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
                () -> Assertions.assertEquals(1L, perfumeReviewBoard.getLikeCount()),
                () -> Assertions.assertEquals(-1L, perfumeReviewBoard.getUnlikeCount())
        );
    }


}
