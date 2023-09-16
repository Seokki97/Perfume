package com.example.perfume.survey.domain;


import com.example.perfume.survey.exception.ScentNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScentTypeTest {


    @DisplayName("선택한 향기에 따른 답변을 제시한다")
    @Test
    void getFeatureAnswer() {
        Survey survey = Survey.builder()
                .scentAnswer("우디")
                .build();

        String actual = ScentType.getFeature(survey);

        String expected = "당신은 자연에서 나는 듯한 냄새와 잘 어울릴 것 같아요 우디향 향수는 당신을 더욱 매력적인 사람으로 만들거예요.";

        Survey exceptionCase = Survey.builder()
                .scentAnswer("오류")
                .build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected, actual),
                () -> Assertions.assertThrows(ScentNotFoundException.class, () -> ScentType.getFeature(exceptionCase))
        );
    }

}
