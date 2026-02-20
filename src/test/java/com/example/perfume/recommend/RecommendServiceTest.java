package com.example.perfume.recommend;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.application.port.in.MemberUseCase;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.repository.RecommendRepository;
import com.example.perfume.recommend.service.RecommendationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RecommendServiceTest {

    @Mock
    private RecommendRepository recommendRepository;

    @InjectMocks
    private RecommendationService recommendationService;
    @Mock
    private MemberUseCase memberService;

    @DisplayName("추천받은 항목을 지운다.")
    @Test
    void deleteRecommendList() {
        Mockito.doNothing().when(recommendRepository).deleteAll();

        recommendationService.deleteRecommendedData();

        Mockito.verify(recommendRepository, Mockito.times(1)).deleteAll();
    }

    @DisplayName("추천받은 향수 목록을 조회한다.")
    @Test
    void showRecommendedPerfume() {
        Member member = Member.builder()
                .memberId(12l)
                .build();
        Recommendation recommendation = Recommendation.builder()
                .member(member)
                .build();
        List<Recommendation> recommendedList = new ArrayList<>();
        recommendedList.add(recommendation);

        Mockito.when(memberService.findMemberById(any())).thenReturn(member);
        Mockito.when(recommendRepository.findByMemberId(any())).thenReturn(recommendedList);

        Long actualId = recommendationService.showRecommendedPerfume(12l)
                .getRecommendationList()
                .get(0)
                .getMember()
                .getMemberId();
        Assertions.assertAll(
                () -> Assertions.assertEquals(12, actualId)
        );
    }
}
