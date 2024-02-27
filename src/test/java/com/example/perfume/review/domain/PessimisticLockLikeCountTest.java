package com.example.perfume.review.domain;

import com.example.perfume.review.domain.review.LikeCount;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.repository.ReviewLikeRepository;
import com.example.perfume.review.service.ReviewLikeService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PessimisticLockLikeCountTest {

    @Autowired
    private ReviewLikeService reviewLikeService;

    @Autowired
    private ReviewBoardRepository reviewBoardRepository;

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    @BeforeEach
    private void before() {
        LikeCount likeCount = new LikeCount(0l, 0l);

        PerfumeReviewBoard perfumeReviewBoard = PerfumeReviewBoard.builder().boardId(30L).content(null).member(null)
                .title("test").likeCount(likeCount).build();

        reviewBoardRepository.save(perfumeReviewBoard);
    }

    @AfterEach
    private void after() {
        reviewBoardRepository.deleteAll();
    }

    @Transactional
    @DisplayName("비관적 락 사용했을 경우 값이 정상적으로 반영된다.")
    @Test
    void request() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    reviewLikeService.pushLike(4L);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        LikeCount likeCount = reviewBoardRepository.findByBoardId(4L).get().getLikeCount();
        Assertions.assertEquals(likeCount.getLikeCount(), 100L);
    }

    @Transactional
    @DisplayName("낙관적 락 사용했을 경우 값이 정상적으로 반영된다.")
    @Test
    void play() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    reviewLikeService.pushLikeByOptimisticLock(9L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        LikeCount likeCount = reviewBoardRepository.findByBoardId(9L).get().getLikeCount();
        Assertions.assertEquals(likeCount.getLikeCount(), 101L);
    }
}
