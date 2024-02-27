package com.example.perfume.review.domain;

import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.PostLike;
import com.example.perfume.review.domain.review.LikeCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LikeCountTest {


    @DisplayName("게시글 좋아요 상태에 따라 좋아요 수를 늘리거나, 싫어요 수를 늘린다")
    @Test
    void calculatePushStatus() {
        LikeCount likeCount = new LikeCount(0l, 0l);
        PostLike likeStatus = new PostLike(null, LikeStatus.LIKE);

        likeCount.calculatePushButton(likeStatus);
        PostLike unlikeStatus = new PostLike(null, LikeStatus.UNLIKE);
        likeCount.calculatePushButton(unlikeStatus);
        Assertions.assertAll(
                () -> Assertions.assertEquals(likeCount.getLikeCount(), 1l),
                () -> Assertions.assertEquals(likeCount.getUnlikeCount(), 1l)
        );
    }
}
