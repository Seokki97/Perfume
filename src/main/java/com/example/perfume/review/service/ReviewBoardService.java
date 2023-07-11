package com.example.perfume.review.service;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.requestDto.PostDeleteRequest;
import com.example.perfume.review.dto.requestDto.PostUpdateRequest;
import com.example.perfume.review.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.responseDto.ReviewBoardResponse;
import com.example.perfume.review.exception.ReviewPostNotFoundException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewBoardService {

    private final PerfumeService perfumeService;

    private final MemberService memberService;

    private final ReviewBoardRepository reviewBoardRepository;


    public ReviewBoardService(ReviewBoardRepository reviewBoardRepository, PerfumeService perfumeService, MemberService memberService) {
        this.reviewBoardRepository = reviewBoardRepository;
        this.perfumeService = perfumeService;
        this.memberService = memberService;
    }

    //리뷰 작성 // 3번사용자 -> 리뷰
    public ReviewBoardResponse writeReview(Long memberId, ReviewBoardRequest boardRequest) {
        Perfume perfume = perfumeService.findPerfumeByName(boardRequest.getPerfumeName());
        Member member = memberService.findByMemberPk(memberId);
        PerfumeReviewBoard perfumeReviewBoard = boardRequest.toEntity(member, boardRequest.getContent(), perfume);

        PerfumeReviewBoard savedBoard = reviewBoardRepository.save(perfumeReviewBoard);
        return ReviewBoardResponse.builder()
                .boardId(savedBoard.getBoardId())
                .build();
    }

    //게시글 수정
    @Transactional
    public ReviewBoardResponse modifyReview(PostUpdateRequest postUpdateRequest) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(postUpdateRequest.getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        perfumeReviewBoard.updatePost(postUpdateRequest.getTitle(), postUpdateRequest.getContent());

        return ReviewBoardResponse.builder()
                .title(postUpdateRequest.getTitle())
                .content(postUpdateRequest.getContent())
                .build();
    }

    //게시글 삭제
    @Transactional
    public Long deleteReviewPost(PostDeleteRequest postDeleteRequest) {
        memberService.findByMemberPk(postDeleteRequest.getMemberId());
        reviewBoardRepository.deleteByBoardId(postDeleteRequest.getBoardId());

        return postDeleteRequest.getBoardId();
    }

    //게시글 조회
    public ReviewBoardResponse showPost(Long boardId) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(boardId)
                .orElseThrow(ReviewPostNotFoundException::new);

        ReviewBoardResponse reviewBoardResponse = ReviewBoardResponse.builder()
                .boardId(perfumeReviewBoard.getBoardId())
                .title(perfumeReviewBoard.getTitle())
                .content(perfumeReviewBoard.getContent())
                .build();

        return reviewBoardResponse;
    }

    //게시글 검색 (향수 이름으로)
    public List<PerfumeReviewBoard> showSearchedPosts(String content) {
        List<PerfumeReviewBoard> perfumeReviewBoards = reviewBoardRepository
                .findByTitleContainingOrContentContaining(content, content);

        return perfumeReviewBoards;
    }
}
