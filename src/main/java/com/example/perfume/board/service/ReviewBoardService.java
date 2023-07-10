package com.example.perfume.board.service;

import com.example.perfume.board.domain.review.PerfumeReviewBoard;
import com.example.perfume.board.dto.requestDto.PostUpdateRequest;
import com.example.perfume.board.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.board.dto.responseDto.ReviewBoardResponse;
import com.example.perfume.board.repository.ReviewBoardRepository;
import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import org.springframework.stereotype.Service;

@Service
public class ReviewBoardService {

    private final PerfumeService perfumeService;

    private final MemberService memberService;

    private final BoardService boardService;


    public ReviewBoardService(BoardService boardService, PerfumeService perfumeService, MemberService memberService) {
        this.boardService = boardService;
        this.perfumeService = perfumeService;
        this.memberService = memberService;
    }

    //리뷰 작성 // 3번사용자 -> 리뷰
    public ReviewBoardResponse writeReview(Long memberId, ReviewBoardRequest boardRequest) {
        Perfume perfume = perfumeService.findPerfumeByName(boardRequest.getPerfumeName());
        Member member = memberService.findMemberById(memberId);
        PerfumeReviewBoard perfumeReviewBoard = boardRequest.toEntity(member, boardRequest.getContent(), perfume);

        PerfumeReviewBoard savedBoard = boardService.savePost(perfumeReviewBoard);
        return ReviewBoardResponse.builder()
                .boardId(savedBoard.getBoardId())
                .build();
    }

    //게시글 수정
    public ReviewBoardResponse modifyReview(PostUpdateRequest postUpdateRequest) {

        PerfumeReviewBoard perfumeReviewBoard = boardService.findBoardById(postUpdateRequest.getBoardId());

        perfumeReviewBoard.updatePost(postUpdateRequest.getTitle(), postUpdateRequest.getContent());

        return ReviewBoardResponse.builder()
                .title(postUpdateRequest.getTitle())
                .content(postUpdateRequest.getContent())
                .build();
    }

    //게시글 삭제


    //게시글 조회
    public ReviewBoardResponse showPost(Long boardId) {
        PerfumeReviewBoard perfumeReviewBoard = boardService.findBoardById(boardId);

        ReviewBoardResponse reviewBoardResponse = ReviewBoardResponse.builder()
                .boardId(perfumeReviewBoard.getBoardId())
                .title(perfumeReviewBoard.getTitle())
                .content(perfumeReviewBoard.getContent())
                .build();

        return reviewBoardResponse;
    }
}
