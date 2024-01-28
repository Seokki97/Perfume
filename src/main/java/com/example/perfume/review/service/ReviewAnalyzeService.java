package com.example.perfume.review.service;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.repository.ReviewBoardRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewAnalyzeService {

    private static final String LIKE_COUNT = "likeCount";
    private static final String UNLIKE_COUNT = "unlikeCount";

    private final ReviewBoardRepository reviewBoardRepository;

    public ReviewAnalyzeService(ReviewBoardRepository reviewBoardRepository) {
        this.reviewBoardRepository = reviewBoardRepository;
    }

    @Transactional
    public List<PerfumeReviewBoard> sortLikeReviews() {

        return reviewBoardRepository.findAllByOrderByLikeCountLikeCountDesc();
    }

    public List<PerfumeReviewBoard> sortUnlikeReviews() {

        return reviewBoardRepository.findAllByOrderByLikeCountUnlikeCountDesc();
    }

    public List<PerfumeReviewBoard> sortLikeReviewsFromSelectedPerfume(String perfumeName) {
        Sort sort = Sort.by(Sort.Direction.DESC, LIKE_COUNT);

        return reviewBoardRepository.findAllByTitleContainingOrContentContaining(perfumeName, perfumeName, sort);
    }

    public List<PerfumeReviewBoard> sortUnlikeReviewsFromSelectedPerfume(String perfumeName) {
        Sort sort = Sort.by(Sort.Direction.DESC, UNLIKE_COUNT);

        return reviewBoardRepository.findAllByTitleContainingOrContentContaining(perfumeName, perfumeName, sort);
    }
}
