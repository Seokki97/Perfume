package com.example.perfume.review.domain;

import static org.mockito.ArgumentMatchers.any;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.review.domain.like.LikeStatus;
import com.example.perfume.review.domain.like.ReviewLike;
import com.example.perfume.review.domain.review.LikeCount;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.like.ReviewLikeRequest;
import com.example.perfume.review.exception.AlreadyPushLikeException;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.repository.ReviewLikeRepository;
import com.example.perfume.review.service.ReviewLikeService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewLikeTest {

    @Mock
    private ReviewLikeRepository reviewLikeRepository;
    @Mock
    private ReviewBoardRepository reviewBoardRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private ReviewLikeService reviewLikeService;

    @DisplayName("이미 푸시버튼을 누른 경우 예외를 발생시킨다")
    @Test
    void validateAlreadyPush() {
        ReviewLikeRequest reviewLikeRequest = new ReviewLikeRequest(1l, 1l, null);
        PerfumeReviewBoard perfumeReviewBoard = PerfumeReviewBoard.builder().boardId(1L).build();
        Member member = Member.builder().build();
        Mockito.when(reviewBoardRepository.findByBoardId(any())).thenReturn(Optional.ofNullable(perfumeReviewBoard));
        Mockito.when(reviewLikeRepository.existsReviewLikeByLikedPostAndPostLikeMember(any(), any())).thenReturn(true);
        Mockito.when(memberRepository.findByMemberId(any())).thenReturn(Optional.ofNullable(member));
        Assertions.assertAll(() -> Assertions.assertThrows(AlreadyPushLikeException.class,
                () -> reviewLikeService.validateAlreadyPushLike(reviewLikeRequest)));

    }

    @DisplayName("게시글 좋아요를 취소할 시 , 좋아요 수가 줄어든다")
    @Test
    void cancelLike() {
        Member member = Member.builder()
                .memberId(1l)
                .build();
        PerfumeReviewBoard perfumeReviewBoard = PerfumeReviewBoard.builder()
                .boardId(1l)
                .member(member)
                .likeCount(new LikeCount(1l, 1l))
                .build();
        ReviewLike reviewLike = new ReviewLike(1l, perfumeReviewBoard, null);
        Mockito.when(reviewBoardRepository.findByBoardId(any())).thenReturn(Optional.ofNullable(perfumeReviewBoard));
        Mockito.when(reviewLikeRepository.deleteReviewLikeByPostLikeMemberAndLikedPost(any(), any())).thenReturn(
                Optional.of(reviewLike));
        Mockito.when(memberRepository.findByMemberId(any())).thenReturn(Optional.ofNullable(member));

        ReviewLikeRequest reviewLikeRequest = new ReviewLikeRequest(1l, 1l, LikeStatus.LIKE);
        reviewLikeService.cancelLikePost(reviewLikeRequest);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0l, perfumeReviewBoard.getLikeCount().getLikeCount())
        );
    }

    @DisplayName("게시글 상태에 따라 좋아요 / 싫어요를 푸쉬하고, 좋아요 수가 반영된다")
    @Test
    void updateUnlike() {
        ReviewLikeRequest likeRequest = new ReviewLikeRequest(1l, 1l, LikeStatus.LIKE);
        ReviewLikeRequest unlikeRequest = new ReviewLikeRequest(1l, 1l, LikeStatus.UNLIKE);
        PerfumeReviewBoard perfumeReviewBoard = PerfumeReviewBoard.builder()
                .boardId(1L)
                .likeCount(new LikeCount(0l, 0l))
                .build();
        Member member = Member.builder().build();

        Mockito.when(reviewBoardRepository.findByBoardId(any())).thenReturn(Optional.ofNullable(perfumeReviewBoard));
        Mockito.when(reviewLikeRepository.existsReviewLikeByLikedPostAndPostLikeMember(any(), any())).thenReturn(false);
        Mockito.when(memberRepository.findByMemberId(any())).thenReturn(Optional.ofNullable(member));

        reviewLikeService.pushLikeOrUnlike(likeRequest);
        reviewLikeService.pushLikeOrUnlike(unlikeRequest);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1l, perfumeReviewBoard.getLikeCount().getLikeCount()),
                () -> Assertions.assertEquals(1l, perfumeReviewBoard.getLikeCount().getUnlikeCount())
        );
    }
}
