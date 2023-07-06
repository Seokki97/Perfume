package com.example.perfume.board.service;

import com.example.perfume.board.domain.review.PerfumeReviewBoard;
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

    private final ReviewBoardRepository reviewBoardRepository;

    private final PerfumeService perfumeService;

    private final MemberService memberService;


    public ReviewBoardService(ReviewBoardRepository reviewBoardRepository, PerfumeService perfumeService, MemberService memberService) {
        this.reviewBoardRepository = reviewBoardRepository;
        this.perfumeService = perfumeService;
        this.memberService = memberService;
    }

    //리뷰 작성
    public ReviewBoardResponse writeReview(Long memberId, ReviewBoardRequest boardRequest) {
        Perfume perfume = perfumeService.findPerfumeByName(boardRequest.getPerfumeName());
        Member member = memberService.findMemberById(memberId);
        PerfumeReviewBoard perfumeReviewBoard = boardRequest.toEntity(member, boardRequest.getContent(), perfume);

        PerfumeReviewBoard savedBoard = reviewBoardRepository.save(perfumeReviewBoard);
        return ReviewBoardResponse.builder()
                .boardId(savedBoard.getBoardId())
                .build();
    }

    //게시글 수정


    //게시글 삭제


    //게시글 조회

}
