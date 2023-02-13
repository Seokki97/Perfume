package com.example.perfume.survey.domain;

import com.example.perfume.survey.exception.SurveyNotFoundException;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;

@Getter
public enum ScentType {

    CITRUS("시트러스", "당신은 오렌지나 자몽처럼 상큼한 향을 좋아하시는군요! 신선한 향은 당신을 더욱 매력적으로 보이게 할 거예요 :)"),
    WOODY("우디", "당신은 자연에서 나는 듯한 냄새와 잘 어울릴 것 같아요! 우디향 향수는 당신을 더욱 매력적인 사람으로 만들거예요 :)"),
    SOAPY("소피", "당신은 은은하게 나는 비누향과 잘 어울릴 것 같아요! 깔끔하고 단정한 이미지를 만들어보아요 :)"),
    FRUITY("프루티", "당신은 달콤한 과일같은 향기와 잘 어울릴 것 같아요! 이런 향수를 쓰면 지나간 사람들이 한번씩 뒤돌아볼 것 같아요 :)"),
    FLORAL("플로럴", "당신은 우아하고 고급스러운 꽃 향과 잘 어울릴 것 같아요! 꽃 향기로 당신의 매력을 어필해보세요 :)"),
    VANILLA("바닐라", "당신은 달짝지근한 향수와 잘 어울릴 것 같아요! 은은하게 퍼지는 향은 당신을 더욱 매력적인 사람으로 만들거예요 :)");

    private String scent;
    private String feature;

    ScentType(String scent, String feature) {
        this.scent = scent;
        this.feature = feature;
    }

    public static String getFeature(Survey survey) {
        String expectedScent = survey.getScentAnswer();
        ScentType scentType = Arrays.stream(ScentType.values()).filter(scent -> scent.getScent().equals(expectedScent))
                .findAny().orElseThrow(SurveyNotFoundException::new);
        return scentType.getFeature();
    }


}
