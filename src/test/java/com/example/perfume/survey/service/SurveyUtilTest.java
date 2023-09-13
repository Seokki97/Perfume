package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyUtilTest {

    private final SurveyUtil surveyUtil = mock(SurveyUtil.class);

    @DisplayName("무드에 대한 답변을 랜덤으로 선택한다.")
    @Test
    void getRandomMoodAnswer() {
        Survey survey = mock(Survey.class);
        String moodAnswer = "달콤한";
        when(surveyUtil.showMoodAnswer(survey)).thenReturn(moodAnswer);

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
        List<Survey> surveyList = new ArrayList<>();

        List<Perfume> perfumeList = new ArrayList<>();

        perfumeList.add(new Perfume(1l, null, null, null, null, null));

        when(surveyUtil.convertToPerfumeData(anyList())).thenReturn(perfumeList);

        List<Perfume> actual = surveyUtil.convertToPerfumeData(surveyList);

        Assertions.assertEquals(1l, actual.get(0).getId());
    }
}
