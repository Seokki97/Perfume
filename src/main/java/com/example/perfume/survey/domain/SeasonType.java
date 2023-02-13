package com.example.perfume.survey.domain;

import com.example.perfume.survey.exception.SurveyNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SeasonType {
    SPRING("봄", "봄에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요!"),
    SUMMER("여름", "여름에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요!"),
    AUTUMN("가을", "가을에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요!"),
    WINTER("겨울", "겨울에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요!"),
    FOUR_SEASON("무관", "어느 계절에 써도 괜찮은 향수에요! 사계절 내내 당신의 매력을 퍼뜨려봐요 :)");

    private String season;

    private String feature;

    SeasonType(String season, String feature) {
        this.season = season;
        this.feature = feature;
    }

    public static String getFeature(Survey survey) {
        String expectedSeason = survey.getSeasonAnswer();
        SeasonType seasonType = Arrays.stream(SeasonType.values())
                .filter(season -> season.getSeason().equals(expectedSeason)).findAny()
                .orElseThrow(SurveyNotFoundException::new);
        return seasonType.getFeature();
    }
}
