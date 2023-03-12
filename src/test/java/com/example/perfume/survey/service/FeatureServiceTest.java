package com.example.perfume.survey.service;


import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.MoodType;
import com.example.perfume.survey.domain.ScentType;
import com.example.perfume.survey.domain.SeasonType;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FeatureServiceTest {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private FeatureService featureService;

    @DisplayName("선택된 향에 맞는 메세지가 응답된다.")
    @Test
    void selectScent(){
        Survey survey = Survey.builder()
                .id(1l)
                .genderAnswer("여자")
                .scentAnswer("플로럴")
                .moodAnswer("상큼한")
                .styleAnswer("포멀")
                .seasonAnswer("여름")
                .build();
        surveyRepository.save(survey);
        String actual = featureService.selectScent(survey.getId());

        String expected = ScentType.FLORAL.getFeature();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("선택된 계절에 맞는 메세지가 응답된다.")
    @Test
    void selectSeason(){
        Survey survey = Survey.builder()
                .id(1l)
                .genderAnswer("여자")
                .scentAnswer("플로럴")
                .moodAnswer("상큼한")
                .styleAnswer("포멀")
                .seasonAnswer("여름")
                .build();

        surveyRepository.save(survey);
        String actual = featureService.selectSeason(survey.getId());

        String expected = SeasonType.SUMMER.getFeature();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("선택된 무드에 맞는 메세지가 응답된다.")
    @Test
    void selectMood(){
        Survey survey = Survey.builder()
                .id(1l)
                .genderAnswer("여자")
                .scentAnswer("플로럴")
                .moodAnswer("상큼한")
                .styleAnswer("포멀")
                .seasonAnswer("여름")
                .build();

        surveyRepository.save(survey);
        String actual = featureService.selectMood(survey.getId());

        String expected = MoodType.FRESH.getMessage();

        assertThat(actual).isEqualTo(expected);

    }
}
