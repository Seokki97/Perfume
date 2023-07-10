package com.example.perfume.board.service;

import com.example.perfume.board.domain.review.PerfumeReviewBoard;
import com.example.perfume.board.dto.responseDto.ReviewBoardResponse;
import com.example.perfume.board.repository.ReviewBoardRepository;
import com.example.perfume.post.exception.PostNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final ReviewBoardRepository reviewBoardRepository;


    public BoardService(ReviewBoardRepository reviewBoardRepository) {
        this.reviewBoardRepository = reviewBoardRepository;
    }

    public PerfumeReviewBoard findBoardById(Long boardId) {

        return reviewBoardRepository.findById(boardId).orElseThrow(PostNotFoundException::new);
    }

    public PerfumeReviewBoard savePost(PerfumeReviewBoard perfumeReviewBoard){
        return reviewBoardRepository.save(perfumeReviewBoard);
    }

}
