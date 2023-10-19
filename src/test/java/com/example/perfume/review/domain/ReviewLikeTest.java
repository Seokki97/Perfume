package com.example.perfume.review.domain;

import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewLikeTest {

    @DisplayName("게시글을 Like상태로 전환한다. 이미 Like상태일 경우 Canceled상태로 전환한다.")
    @Test
    void updateLike() {
        ReviewLike statusLike = ReviewLike.builder()
                .likeStatus(LikeStatus.LIKE)
                .build();

        ReviewLike statusNothing = ReviewLike.builder()
                .likeStatus(null)
                .build();

        ReviewLike statusUnlike = ReviewLike.builder()
                .likeStatus(LikeStatus.UNLIKE)
                .build();

        Assertions.assertAll(
                //Unlike 상태일 경우 -> Like상태로
                () -> Assertions.assertEquals(LikeStatus.LIKE, LikeStatus.updateLike(statusUnlike.getLikeStatus())),
                //Like 상태일 경우 -> Canceled상태로
                () -> Assertions.assertEquals(LikeStatus.CANCELED, LikeStatus.updateLike(statusLike.getLikeStatus())),
                //아무 상태도 아닐 경우 -> Like상태로
                () -> Assertions.assertEquals(LikeStatus.LIKE, LikeStatus.updateLike(statusNothing.getLikeStatus()))
                );
    }

    @DisplayName("게시글을 Unlike상태로 전환한다. 이미 Unlike 상태일 경우 Canceled상태로 전환한다")
    @Test
    void updateUnlike() {
        ReviewLike statusLike = ReviewLike.builder()
                .likeStatus(LikeStatus.LIKE)
                .build();

        ReviewLike statusNothing = ReviewLike.builder()
                .likeStatus(null)
                .build();

        ReviewLike statusUnlike = ReviewLike.builder()
                .likeStatus(LikeStatus.UNLIKE)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(LikeStatus.UNLIKE, LikeStatus.updateUnLike(statusLike.getLikeStatus())),
                () -> Assertions.assertEquals(LikeStatus.UNLIKE, LikeStatus.updateUnLike(statusNothing.getLikeStatus())),
                () -> Assertions.assertEquals(LikeStatus.CANCELED, LikeStatus.updateUnLike(statusUnlike.getLikeStatus()))
        );
    }

}
