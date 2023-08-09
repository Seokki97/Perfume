package com.example.perfume.review.service;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.repository.ReviewBoardRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewAnalyzeService {
    private final ReviewBoardRepository reviewBoardRepository;

    public ReviewAnalyzeService(ReviewBoardRepository reviewBoardRepository) {
        this.reviewBoardRepository = reviewBoardRepository;
    }

    //라이크 많은 수 정렬
    public List<PerfumeReviewBoard> sortByMostLikeReviews() {
        List<PerfumeReviewBoard> selectedPerfume = reviewBoardRepository
                .findAllByLikeCount(Sort.Direction.DESC,"likeCount");

        return selectedPerfume;
    }

    //Unlike 많은 수 정렬
    public List<PerfumeReviewBoard> sortByMostUnlikeReviews() {
        List<PerfumeReviewBoard> selectedPerfume = reviewBoardRepository
                .findAllByLikeCount(Sort.Direction.DESC,"unlikeCount");

        return selectedPerfume;
    }
    //선택한 향수의 LIKE 많은 수 정렬
}
