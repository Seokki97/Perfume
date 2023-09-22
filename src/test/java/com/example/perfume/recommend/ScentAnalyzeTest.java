package com.example.perfume.recommend;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.repository.RecommendRepository;
import com.example.perfume.recommend.service.analyze.AnalyzeUtil;
import com.example.perfume.recommend.service.analyze.ScentAnalyze;
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
public class ScentAnalyzeTest {

    @Mock
    private RecommendRepository recommendRepository;

    @InjectMocks
    private ScentAnalyze scentAnalyze;

    @InjectMocks
    private AnalyzeUtil analyzeUtil;


    @DisplayName("추천된 항목들을 추출한다.")
    @Test
    void extractElement() {
        Recommendation recommendation = Recommendation.builder()
                .scentAnswer("시트러스")
                .build();

        Recommendation recommendation1 = Recommendation.builder()
                .scentAnswer("우디")
                .build();

        List<Recommendation> list = new ArrayList<>();

        list.add(recommendation);
        list.add(recommendation1);
        when(recommendRepository.findByMemberId(any())).thenReturn(list);

        List<String> perfumeList = scentAnalyze.extractRecommendedElement(1l);

        Assertions.assertAll(
                () -> Assertions.assertEquals(recommendation.getScentAnswer(), perfumeList.get(0)),
                () -> Assertions.assertEquals(recommendation1.getScentAnswer(), perfumeList.get(1))
        );
    }
}
