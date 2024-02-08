package com.example.perfume.review.service;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.responseDto.ReviewAnalyzeResponse;
import com.example.perfume.review.repository.ReviewBoardRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewAnalyzeService {
    private final ReviewBoardRepository reviewBoardRepository;

    public ReviewAnalyzeService(ReviewBoardRepository reviewBoardRepository) {
        this.reviewBoardRepository = reviewBoardRepository;
    }

    public ReviewAnalyzeResponse sortLikeReviews() {
        return new ReviewAnalyzeResponse(
                reviewBoardRepository.findByLikeCountDesc());
    }

    public List<PerfumeReviewBoard> sortUnlikeReviews() {
        return reviewBoardRepository.findByUnLikeCountDesc();
    }
}
