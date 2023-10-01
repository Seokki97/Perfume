package com.example.perfume.review;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.review.domain.Content;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.review.requestDto.PostDeleteRequest;
import com.example.perfume.review.dto.review.requestDto.PostUpdateRequest;
import com.example.perfume.review.dto.review.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.review.responseDto.ReviewBoardResponse;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.ReviewBoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewBoardServiceTest {

    @InjectMocks
    private ReviewBoardService reviewBoardService;

    @Mock
    private ReviewBoardRepository reviewBoardRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private PerfumeService perfumeService;


    @DisplayName("향수 리뷰를 작성한다")
    @Test
    void writeReview() {
        Long memberId = 1L;
        Content content = new Content("임시 내용", "임시 url");

        ReviewBoardRequest boardRequest = ReviewBoardRequest.builder()
                .writer(memberId)
                .title("임시 제목")
                .content(content)
                .perfumeName("에르메스 오드시트론느와")
                .build();

        Member mockMember = Member.builder()
                .memberId(1L)
                .build();

        Perfume mockPerfume = Perfume.builder()
                .perfumeName("에르메스 오드시트론느와")
                .build();

        PerfumeReviewBoard mockBoard = PerfumeReviewBoard.builder()
                .boardId(1L)
                .member(mockMember)
                .title(boardRequest.getTitle())
                .build();

        when(memberService.findByMemberPk(memberId)).thenReturn(mockMember);
        when(perfumeService.findPerfumeByName(boardRequest.getPerfumeName())).thenReturn(mockPerfume);
        when(reviewBoardRepository.save(any(PerfumeReviewBoard.class))).thenReturn(mockBoard);

        ReviewBoardResponse result = reviewBoardService.writeReview(memberId, boardRequest);


        Assertions.assertEquals(mockBoard.getBoardId(), result.getBoardId());

    }

    @DisplayName("게시글 수정하면 수정된 내용이 반영된다.")
    @Test
    void updatePost() {
        Long boardId = 1L;

        Content resultContent = new Content("변경", "변경");

        PostUpdateRequest postUpdateRequest = PostUpdateRequest.builder()
                .boardId(boardId)
                .title("변경")
                .content(resultContent)
                .build();

        PerfumeReviewBoard mockBoard = PerfumeReviewBoard.builder()
                .title("변경")
                .content(resultContent)
                .boardId(1L)
                .build();

        when(reviewBoardRepository.findByBoardId(boardId)).thenReturn(Optional.of(mockBoard));

        ReviewBoardResponse result = reviewBoardService.modifyReview(postUpdateRequest);

        Assertions.assertAll(
                () -> Assertions.assertEquals(result.getBoardId(), postUpdateRequest.getBoardId()),
                () -> Assertions.assertEquals(result.getTitle(), postUpdateRequest.getTitle()),
                () -> Assertions.assertEquals(result.getContent(), postUpdateRequest.getContent())
        );
    }

    @DisplayName("리뷰를 삭제한다.")
    @Test
    void deleteReview() {
        Long memberId = 1L;
        Long boardId = 1L;

        PostDeleteRequest postDeleteRequest = new PostDeleteRequest(1L, 1L);

        Member mockMember = Member.builder()
                .memberId(memberId)
                .build();

        PerfumeReviewBoard mockReviewBoard = PerfumeReviewBoard.builder()
                .boardId(boardId)
                .build();

        when(memberService.findByMemberPk(memberId)).thenReturn(mockMember);
        doNothing().when(reviewBoardRepository).deleteByBoardId(mockReviewBoard.getBoardId());

        Assertions.assertDoesNotThrow(() -> reviewBoardService.deleteReviewPost(postDeleteRequest));

    }

    @DisplayName("게시글을 단일 조회한다.")
    @Test
    void showOnePost() {
        Long boardId = 1L;

        PerfumeReviewBoard mockBoard = PerfumeReviewBoard.builder()
                .boardId(boardId)
                .build();

        when(reviewBoardRepository.findByBoardId(boardId)).thenReturn(Optional.of(mockBoard));

        ReviewBoardResponse result = reviewBoardService.showPost(boardId);

        Assertions.assertEquals(boardId, result.getBoardId());
    }

    @DisplayName("게시글 검색")
    @Test
    void searchPost() {

        PerfumeReviewBoard expectedCaseOne = PerfumeReviewBoard.builder()
                .title("조말론 냄새 좋아요!")
                .build();

        PerfumeReviewBoard expectedCaseTwo = PerfumeReviewBoard.builder()
                .content(new Content("구찌 사랑해", "url"))
                .build();

        List<PerfumeReviewBoard> mockReviewBoard = new ArrayList<>();

        mockReviewBoard.add(expectedCaseOne);
        mockReviewBoard.add(expectedCaseTwo);

        when(reviewBoardRepository.findByTitleContainingOrContentContaining(anyString(), eq("구찌"))).thenReturn(mockReviewBoard);

        List<PerfumeReviewBoard> result = reviewBoardService.showSearchedPosts("구찌");

        Assertions.assertEquals(expectedCaseTwo.getBoardId(), result.get(1).getBoardId());
    }

}
