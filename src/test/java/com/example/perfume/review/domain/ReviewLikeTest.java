package com.example.perfume.review.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewLikeTest {

    @DisplayName("게시글을 Like상태로 전환한다. 이미 Like상태일 경우 Canceled상태로 전환한다.")
    @Test
    void updateLike() {
    }

    @DisplayName("게시글을 Unlike상태로 전환한다. 이미 Unlike 상태일 경우 Canceled상태로 전환한다")
    @Test
    void updateUnlike() {

    }

}
