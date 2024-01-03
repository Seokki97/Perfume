package com.example.perfume.review.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.requestDto.PostDeleteRequest;
import com.example.perfume.review.dto.review.requestDto.PostUpdateRequest;
import com.example.perfume.review.dto.review.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.review.responseDto.ReviewBoardResponse;
import com.example.perfume.review.exception.ReviewPostNotFoundException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewBoardService {

    private final MemberService memberService;

    private final ReviewBoardRepository reviewBoardRepository;

    public ReviewBoardService(ReviewBoardRepository reviewBoardRepository, MemberService memberService) {
        this.reviewBoardRepository = reviewBoardRepository;
        this.memberService = memberService;
    }

    @Transactional
    public ReviewBoardResponse writeReview(Long memberId, ReviewBoardRequest boardRequest) {
        Member member = memberService.findByMemberPk(memberId);
        PerfumeReviewBoard perfumeReviewBoard = boardRequest.toEntity(member, boardRequest.getContent());

        perfumeReviewBoard.validatePostDuplication(reviewBoardRepository);
        PerfumeReviewBoard savedBoard = reviewBoardRepository.save(perfumeReviewBoard);

        return ReviewBoardResponse.builder()
                .boardId(savedBoard.getBoardId())
                .build();
    }

    //게시글 수정
    @Transactional
    public ReviewBoardResponse modifyReviewTitleAndContent(PostUpdateRequest postUpdateRequest) {

        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(postUpdateRequest.getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        perfumeReviewBoard.updatePost(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
        perfumeReviewBoard.validatePostDuplication(reviewBoardRepository);

        return ReviewBoardResponse.builder()
                .boardId(perfumeReviewBoard.getBoardId())
                .title(postUpdateRequest.getTitle())
                .content(postUpdateRequest.getContent())
                .build();
    }

    @Transactional
    public ReviewBoardResponse modifyReviewTitle(PostUpdateRequest postUpdateRequest) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(postUpdateRequest.getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        perfumeReviewBoard.updateTitle(postUpdateRequest.getTitle());
        perfumeReviewBoard.validatePostDuplication(reviewBoardRepository);

        return ReviewBoardResponse.builder()
                .boardId(perfumeReviewBoard.getBoardId())
                .title(postUpdateRequest.getTitle())
                .build();
    }

    @Transactional
    public ReviewBoardResponse modifyReviewContent(PostUpdateRequest postUpdateRequest) {
        PerfumeReviewBoard perfumeReviewBoard = reviewBoardRepository.findByBoardId(postUpdateRequest.getBoardId())
                .orElseThrow(ReviewPostNotFoundException::new);

        perfumeReviewBoard.updateContent(postUpdateRequest.getContent());

        return ReviewBoardResponse.builder()
                .boardId(perfumeReviewBoard.getBoardId())
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

        return ReviewBoardResponse.builder()
                .boardId(perfumeReviewBoard.getBoardId())
                .title(perfumeReviewBoard.getTitle())
                .content(perfumeReviewBoard.getContent())
                .likeCount(perfumeReviewBoard.getLikeCount())
                .unlikeCount(perfumeReviewBoard.getUnlikeCount())
                .build();
    }

    //게시글 검색 (향수 이름으로)
    public List<PerfumeReviewBoard> showSearchedPosts(String perfumeName) {
        return reviewBoardRepository
                .findByTitleContainingOrContentContaining(perfumeName, perfumeName);
    }

    //내가 남긴 게시글 조회
    public List<PerfumeReviewBoard> showMyReviewPost(Long memberId) {
        return reviewBoardRepository.findByWriter(memberId);
    }
}
