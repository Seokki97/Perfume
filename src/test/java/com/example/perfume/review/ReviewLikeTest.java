package com.example.perfume.review;


import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.repository.ReviewLikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewLikeTest {

    @DisplayName("이미 좋아요가 달려있을 경우 좋아요가 취소된다.")
    @Test
    public void likePost() {
        Member member = Member.builder()
                .memberId(1L)
                .build();

        PerfumeReviewBoard reviewPostTwo = PerfumeReviewBoard.builder()
                .boardId(1L)
                .build();

        ReviewLike nonStatus = ReviewLike.builder()
                .reviewId(1L)
                .likedPost(reviewPostTwo)
                .build();

        nonStatus.updateLike();
        Assertions.assertAll(
                //아무 상태가 아닐 경우 좋아요 수가 늘어난다.
                () -> Assertions.assertEquals(1L, nonStatus.getLikedPost().getLikeCount())
        );
    }


}
