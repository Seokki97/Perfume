package com.example.perfume.review.domain;

import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.ReviewLikeService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PessimisticLockLikeCountTest {

    @Autowired
    private ReviewLikeService reviewLikeService;

    @Autowired
    private ReviewBoardRepository reviewBoardRepository;


    @DisplayName("Synchronized를 사용하여 동시성 문제를 해결한다")
    @Test
    void test() throws InterruptedException {
        int threadCount = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    reviewLikeService.pushLikeByOptimisticLock(83l);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        long likeCount = reviewBoardRepository.findByBoardId(83l).get().getLikeCount()
                .getLikeCount();
        Assertions.assertEquals(likeCount, 100L);
    }
}
