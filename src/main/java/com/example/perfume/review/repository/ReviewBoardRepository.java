package com.example.perfume.review.repository;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewBoardRepository extends JpaRepository<PerfumeReviewBoard, Long> {

    @Lock(value = LockModeType.OPTIMISTIC)
    Optional<PerfumeReviewBoard> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);

    List<PerfumeReviewBoard> findByTitleContainingOrContentContaining(String title, String content);

    List<PerfumeReviewBoard> findByWriter(Member writer);

    List<PerfumeReviewBoard> findAllByTitleContainingOrContentContaining(String title, String content, Sort sort);


    @Query("SELECT board FROM perfume_review_board board JOIN FETCH board.writer ORDER BY board.likeCount.likeCount DESC")
    List<PerfumeReviewBoard> findByLikeCountDesc();

    @Query("SELECT board FROM perfume_review_board board JOIN FETCH board.writer ORDER BY board.likeCount.unlikeCount DESC")
    List<PerfumeReviewBoard> findByUnLikeCountDesc();

    boolean existsByTitle(String title);
}
