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

    private static final String LIKE_COUNT = "likeCount";
    private static final String UNLIKE_COUNT = "unlikeCount";

    private final ReviewBoardRepository reviewBoardRepository;

    public ReviewAnalyzeService(ReviewBoardRepository reviewBoardRepository) {
        this.reviewBoardRepository = reviewBoardRepository;
    }

    //라이크 많은 수 정렬
    public List<PerfumeReviewBoard> sortLikeReviews() {

        return reviewBoardRepository.findAllByOrderByLikeCountDesc();
    }

    //Unlike 많은 수 정렬
    public List<PerfumeReviewBoard> sortUnlikeReviews() {

        return reviewBoardRepository.findAllByOrderByUnlikeCountDesc();

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
