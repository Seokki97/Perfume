package com.example.perfume.recommend;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.perfume.recommend.dto.analyze.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.dto.analyze.RankingResponse;
import com.example.perfume.recommend.dto.analyze.ScentAnalyzeResponse;
import com.example.perfume.recommend.service.analyze.AnalyzeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnalyzeServiceTest {

    @Mock
    private AnalyzeService analyzeService;

    @DisplayName("분석된 데이터를 반환한다.")
    @Test
    void responseAnalyzedData() {

        PerfumeAnalyzeResponse perfumeAnalyzeResponse = new PerfumeAnalyzeResponse(1l, "조말론");
        ScentAnalyzeResponse scentAnalyzeResponse = new ScentAnalyzeResponse("시트러스", 2l);

        RankingResponse rankingResponse = RankingResponse.builder()
                .perfumeAnalyzeResponse(perfumeAnalyzeResponse)
                .scentAnalyzeResponse(scentAnalyzeResponse)
                .build();
        when(analyzeService.responseAnalyzedData(any())).thenReturn(rankingResponse);

        RankingResponse actual = analyzeService.responseAnalyzedData(1l);
        Assertions.assertAll(
                () -> Assertions.assertEquals(actual.getPerfumeAnalyzeObject(), perfumeAnalyzeResponse),
                () -> Assertions.assertEquals(actual.getScentAnalyzeObject(), scentAnalyzeResponse)
        );
    }
}
