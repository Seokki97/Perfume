package com.example.perfume.review.repository;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewBoardRepository extends JpaRepository<PerfumeReviewBoard, Long> {
    boolean existsByBoardId(Long boardId);

    Optional<PerfumeReviewBoard> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);

    List<PerfumeReviewBoard> findByTitleContainingOrContentContaining(String title, String content);

    List<PerfumeReviewBoard> findByWriter(Long memberId);

}
