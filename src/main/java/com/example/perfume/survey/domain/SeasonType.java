package com.example.perfume.survey.domain;

import com.example.perfume.survey.exception.SeasonNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SeasonType {

    SPRING("봄", "봄에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요!"),
    SUMMER("여름", "여름에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요."),
    AUTUMN("가을", "가을에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요."),
    WINTER("겨울", "겨울에 잘 어울리는 향이에요. 그 외에 계절에는 안 어울릴 수도 있으니 피해주는게 좋을 것 같아요."),
    FOUR_SEASON("봄 여름 가을 겨울", "어느 계절에 써도 괜찮은 향수에요. 사계절 내내 당신의 매력을 퍼뜨려봐요."),
    NOT_SPRING("봄 가을 겨울", "이 향수는 여름에는 어울리지 않아요. 여름에만 사용하는 것을 피해주세요."),
    SPRING_SUMMER("봄 여름", "이 향수는 봄과 여름에 잘 어울려요. 그 외의 계절에는 안 어울릴 수도 있어요."),
    SUMMER_AUTUMN("여름 가을", "이 향수는 여름과 가을에 쓰는 것을 추천드려요. 그 외에 계절은 고민을 해보세요."),
    AUTUMN_WINTER("가을 겨울", "이 향수는 가을과 겨울에 잘 어울리는 향수예요. 그 외에 계절에는 안 어울릴 수도 있어요."),
    WINTER_SPRING("봄 겨울", "이 향수는 봄과 겨울에 잘 어울리는 향수예요. 그 외에 계절에는 안 어울릴 수도 있어요."),
    NOT_WINTER("봄 여름 가을","이 향수는 겨울과는 어울리지 않아요. 겨울에만 사용하는 것을 피해주세요. "),
    SPRING_AUTUMN("봄 가을", "이 향수는 봄 가을에 잘 어울리는 향수예요, 그 외의 계절에는 안 어울릴 수도 있어요.");
    private final String season;

    private final String feature;

    SeasonType(String season, String feature) {
        this.season = season;
        this.feature = feature;
    }

    public static String getFeature(Survey survey) {
        String expectedSeason = survey.getSeasonAnswer();
        SeasonType seasonType = Arrays.stream(SeasonType.values())
                .filter(season -> season.getSeason().equals(expectedSeason)).findAny()
                .orElseThrow(SeasonNotFoundException::new);
        return seasonType.getFeature();
    }
}
