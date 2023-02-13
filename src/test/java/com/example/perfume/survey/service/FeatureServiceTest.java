package com.example.perfume.survey.service;


import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.ScentType;
import com.example.perfume.survey.domain.SeasonType;
import com.example.perfume.survey.message.SeasonMessage;
import com.example.perfume.survey.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FeatureServiceTest {

    @Autowired
    private PerfumeRepository perfumeRepository;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private FeatureService featureService;

    @DisplayName("선택된 향에 맞는 메세지가 출력된다.")
    @Test
    void selectScent(){
        long perfumeId = 1l;

        String actual = featureService.selectScent(perfumeId);

        String expected = ScentType.FLORAL.getFeature();

        assertAll(
                () -> assertEquals(expected,actual)
        );
    }

    @DisplayName("선택된 계절에 맞는 메세지가 출력된다.")
    @Test
    void selectSeason(){
        long perfumeId = 1l;

        String actual = featureService.selectSeason(perfumeId);

        String expected = SeasonType.WINTER.getFeature();

        assertAll(
                () -> assertEquals(expected,actual)
        );
    }
}
