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
    public List<PerfumeReviewBoard> sortLikeReviews() {
        Sort sort = Sort.by(Sort.Direction.DESC, "likeCount");

        return reviewBoardRepository.findAllByLikeCount(sort);
    }

    //Unlike 많은 수 정렬
    public List<PerfumeReviewBoard> sortUnlikeReviews() {
        Sort sort = Sort.by(Sort.Direction.DESC, "unlikeCount");

        return reviewBoardRepository.findAllByLikeCount(sort);

    }

    public List<PerfumeReviewBoard> sortLikeReviewsFromSelectedPerfume(String perfumeName) {
        Sort sort = Sort.by(Sort.Direction.DESC, "likeCount");

        return reviewBoardRepository.findAllByTitleContainingOrContentContaining(perfumeName, perfumeName, sort);
    }

    public List<PerfumeReviewBoard> sortUnlikeReviewsFromSelectedPerfume(String perfumeName) {
        Sort sort = Sort.by(Sort.Direction.DESC, "likeCount");

        return reviewBoardRepository.findAllByTitleContainingOrContentContaining(perfumeName, perfumeName, sort);
    }
}
