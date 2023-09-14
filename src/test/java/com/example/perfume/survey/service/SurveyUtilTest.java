package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SurveyUtilTest {

    @Autowired
    private SurveyUtil surveyUtil;

    @DisplayName("무드에 대한 답변을 랜덤으로 선택한다.")
    @Test
    void getRandomMoodAnswer() {
        Survey survey = Survey.builder()
                .moodAnswer("달콤한 상큼한")
                .build();

        String actualCaseOne = "달콤한";
        String actualCaseTwo = "상큼한";
        assertThat(surveyUtil.showMoodAnswer(survey))
                .satisfiesAnyOf(
                        result -> assertThat(result).isEqualTo(actualCaseOne),
                        result -> assertThat(result).isEqualTo(actualCaseTwo)
                );
    }

    @DisplayName("SurveyList에서 Perfume객체를 추출한다")
    @Test
    void extractPerfumeData() {
        Perfume perfume = Perfume.builder()
                .id(1l).build();

        Survey survey = Survey.builder()
                .perfume(perfume)
                .build();

        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(survey);

        List<Perfume> actual = surveyUtil.convertToPerfumeData(surveyList);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1l, actual.get(0).getId()),
                () -> Assertions.assertEquals(perfume, actual.get(0))
        );
    }

    @DisplayName("SurveyList가 비어있는지 검증한다")
    @Test
    void isEmptySurveyList() {
        List<Survey> emptyList = Collections.emptyList();

        Survey survey = Survey.builder()
                .build();

        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(survey);

        boolean trueAnswer = surveyUtil.isEmptyRecommendedPerfumeList(emptyList);
        boolean falseAnswer = surveyUtil.isEmptyRecommendedPerfumeList(surveyList);

        Assertions.assertAll(
                () -> Assertions.assertTrue(trueAnswer),
                () -> Assertions.assertFalse(falseAnswer)
        );
    }
}
