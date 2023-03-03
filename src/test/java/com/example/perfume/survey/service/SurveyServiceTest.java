package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SurveyServiceTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyService surveyService;

    @DisplayName("선택된 성별에 따른 설문 결과 리스트를 반환한다. 해당 데이터가 없을 시 SurveyNotFoundException 을 발생시킨다.")
    @Test
    void selectGender(){
        SurveyResponseDto female = SurveyResponseDto.builder()
                .genderAnswer("여자")
                .build();
        SurveyResponseDto male = SurveyResponseDto.builder()
                .genderAnswer("남자")
                .build();
        Survey survey = Survey.builder()
                .genderAnswer("여자")
                .styleAnswer("포멀")
                .id(1l)
                .seasonAnswer("여름")
                .moodAnswer("상큼한")
                .scentAnswer("시트러스").build();
        surveyRepository.save(survey);
        List<Survey> expected = new ArrayList<>();
        expected.add(survey);

        List<Survey> actual = surveyService.filterGenderAnswer(female);

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .isEqualTo(expected),
                () -> assertThrows(SurveyNotFoundException.class, () ->{surveyService.filterGenderAnswer(male);} )
        );
    }

    @DisplayName("선택된 향에 따른 설문 리스트를 반환한다. 없을 시 SurveyNotFoundException 을 발생시킨다.")
    @Test
    void selectScent(){
        SurveyResponseDto citrus = SurveyResponseDto.builder()
                .scentAnswer("시트러스")
                .build();
        SurveyResponseDto vanilla = SurveyResponseDto.builder()
                .scentAnswer("바닐라")
                .build();
        Survey survey = Survey.builder()
                .genderAnswer("여자")
                .styleAnswer("포멀")
                .id(1l)
                .seasonAnswer("여름")
                .moodAnswer("상큼한")
                .scentAnswer("시트러스").build();
        surveyRepository.save(survey);
        List<Survey> expected = new ArrayList<>();
        expected.add(survey);

        List<Survey> actual = surveyService.filterScentAnswer(citrus);

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .isEqualTo(expected),
                () -> assertThrows(SurveyNotFoundException.class, () ->{surveyService.filterGenderAnswer(vanilla);} )
        );
    }

    @DisplayName("선택된 무드에 따른 설문 리스트를 반환한다. 없을 시 SurveyNotFoundException 을 발생시킨다.")
    @Test
    void selectMood(){
        SurveyResponseDto warmth = SurveyResponseDto.builder()
                .moodAnswer("따뜻한")
                .build();
        SurveyResponseDto fresh = SurveyResponseDto.builder()
                .moodAnswer("상큼한")
                .build();
        Survey survey = Survey.builder()
                .genderAnswer("여자")
                .styleAnswer("포멀")
                .id(1l)
                .seasonAnswer("여름")
                .moodAnswer("상큼한")
                .scentAnswer("시트러스").build();
        surveyRepository.save(survey);
        List<Survey> expected = new ArrayList<>();
        expected.add(survey);

        List<Survey> actual = surveyService.filterMoodAnswer(fresh);

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .isEqualTo(expected),
                () -> assertThrows(SurveyNotFoundException.class, () ->{surveyService.filterGenderAnswer(warmth);} )
        );
    }
}
