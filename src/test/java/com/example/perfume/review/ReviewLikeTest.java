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

        PerfumeReviewBoard reviewPost = PerfumeReviewBoard.builder()
                .boardId(1L)
                .build();

        ReviewLike nonStatus = ReviewLike.builder()
                .reviewId(1L)
                .likedPost(reviewPost)
                .build();

        nonStatus.updateLike();
        Assertions.assertAll(
                //아무 상태가 아닐 경우 좋아요 수가 늘어난다.
                () -> Assertions.assertEquals(1L, nonStatus.getLikedPost().getLikeCount())
        );
    }
    @DisplayName("좋아요를 취소하면 좋아요 수가 줄어든다.")
    @Test
    void cancelLike() {
        PerfumeReviewBoard reviewPost = PerfumeReviewBoard.builder()
                .boardId(1L)
                .build();

        ReviewLike expectedCase = ReviewLike.builder()
                .reviewId(1L)
                .likedPost(reviewPost)
                .likeStatus(LikeStatus.LIKE)
                .build();

        ReviewLike alreadyLikeStatus = ReviewLike.builder()
                .reviewId(1L)
                .likedPost(reviewPost)
                .likeStatus(LikeStatus.LIKE)
                .build();

        alreadyLikeStatus.updateLike();
        Assertions.assertAll(
                () -> Assertions.assertEquals(LikeStatus.CANCELED, alreadyLikeStatus.getLikeStatus()),
                () -> Assertions.assertEquals(-1L, expectedCase.getLikedPost().getLikeCount())
        );
    }

    @DisplayName("싫어요 상태일 때 좋아요를 누르면 싫어요가 취소되고 좋아요가 반영된다.")
    @Test
    void displayUnlikeToLike(){
        PerfumeReviewBoard reviewPost = PerfumeReviewBoard.builder()
                .boardId(1L)
                .build();

        ReviewLike expectedCase = ReviewLike.builder()
                .likedPost(reviewPost)
                .likeStatus(LikeStatus.UNLIKE)
                .build();

        expectedCase.updateLike();

        Assertions.assertAll(
                () -> Assertions.assertEquals(LikeStatus.LIKE, expectedCase.getLikeStatus()),
                () -> Assertions.assertEquals(-1L, reviewPost.getUnlikeCount()),
                () -> Assertions.assertEquals(1L, reviewPost.getLikeCount())
        );

    }

    @DisplayName("싫어요 기능의 상태 벼경을 관리한다.")
    @Test
    public void unlikePost() {

    }

}
