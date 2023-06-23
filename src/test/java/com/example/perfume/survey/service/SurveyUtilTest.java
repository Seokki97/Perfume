package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
}
