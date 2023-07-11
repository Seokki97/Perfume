package com.example.perfume.board.repository;

import com.example.perfume.board.domain.review.PerfumeReviewBoard;
import com.example.perfume.perfume.domain.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewBoardRepository extends JpaRepository<PerfumeReviewBoard, Long> {
    boolean existsByBoardId(Long boardId);

    Optional<PerfumeReviewBoard> findByBoardId(Long boardId);

    Optional<PerfumeReviewBoard> deleteByBoardId(Long boardId);

    List<PerfumeReviewBoard> findByTitleContainingOrContentContaining(String title, String content);

}
