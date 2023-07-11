package com.example.perfume.board.repository;

import com.example.perfume.board.domain.review.PerfumeReviewBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewBoardRepository extends JpaRepository<PerfumeReviewBoard, Long> {
}
