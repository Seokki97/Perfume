package com.example.perfume.recommend;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.repository.RecommendRepository;
import com.example.perfume.recommend.service.analyze.PerfumeAnalyze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PerfumeAnalyzeTest {

    @Mock
    private RecommendRepository recommendRepository;

    @InjectMocks
    private PerfumeAnalyze perfumeAnalyze;

    @DisplayName("추천된 항목들을 추출한다.")
    @Test
    void extractElement() {
        Recommendation recommendation = Recommendation.builder()
                .perfume(Perfume.builder().perfumeName("조말론").build())
                .build();

        Recommendation recommendation1 = Recommendation.builder()
                .perfume(Perfume.builder().perfumeName("샤넬").build())
                .build();

        List<Recommendation> list = new ArrayList<>();

        list.add(recommendation);
        list.add(recommendation1);
        when(recommendRepository.findByMemberId(any())).thenReturn(list);

        List<String> perfumeList = perfumeAnalyze.extractRecommendedElement(1l);

        Assertions.assertAll(
                () -> Assertions.assertEquals(recommendation.getPerfume().getPerfumeName(), perfumeList.get(0)),
                () -> Assertions.assertEquals(recommendation1.getPerfume().getPerfumeName(), perfumeList.get(1))
        );
    }
}
