package com.example.perfume.review;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.review.domain.Content;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.requestDto.ReviewBoardRequest;
import com.example.perfume.review.dto.responseDto.ReviewBoardResponse;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.ReviewBoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewBoardServiceTest {

    @InjectMocks
    private ReviewBoardService reviewBoardService;


    @Mock
    private ReviewBoardRepository reviewBoardRepository;

    @Mock
    private MemberService memberService = mock(MemberService.class);

    @Mock
    private PerfumeService perfumeService = mock(PerfumeService.class);


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
                .id(1L)
                .build();

        Perfume mockPerfume = Perfume.builder()
                .perfumeName("에르메스 오드시트론느와")
                .build();

        PerfumeReviewBoard mockBoard = PerfumeReviewBoard.builder()
                .boardId(1L)
                .member(mockMember)
                .perfumeImageUrl(content.getImageUrl())
                .title(boardRequest.getTitle())
                .build();

        when(memberService.findByMemberPk(memberId)).thenReturn(mockMember);
        when(perfumeService.findPerfumeByName(boardRequest.getPerfumeName())).thenReturn(mockPerfume);
        when(reviewBoardRepository.save(any(PerfumeReviewBoard.class))).thenReturn(mockBoard);

        ReviewBoardResponse result = reviewBoardService.writeReview(memberId, boardRequest);


        Assertions.assertEquals(mockBoard.getBoardId(), result.getBoardId());

    }
}
