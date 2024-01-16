package com.example.perfume.review.repository;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewBoardRepository extends JpaRepository<PerfumeReviewBoard, Long> {

    Optional<PerfumeReviewBoard> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);

    List<PerfumeReviewBoard> findByTitleContainingOrContentContaining(String title, String content);

    List<PerfumeReviewBoard> findByWriter(Long memberId);

    List<PerfumeReviewBoard> findAllByTitleContainingOrContentContaining(String title, String content, Sort sort);


    List<PerfumeReviewBoard> findAllByOrderByLikeCountLikeCountDesc();

    List<PerfumeReviewBoard> findAllByOrderByLikeCountUnlikeCountDesc();

    boolean existsByTitle(String title);

}
