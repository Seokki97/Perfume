package com.example.perfume.survey.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Question {

    private String genderAnswer;

    private String scentAnswer;

    private String moodAnswer;

    private String seasonAnswer;

    private String styleAnswer;

    @Builder
    public Question(String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer,
                    String styleAnswer) {
        this.genderAnswer = genderAnswer;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
    }

    public void addQueryParameter() {
        this.genderAnswer = "%" + genderAnswer + "%";
        this.moodAnswer = "%" + moodAnswer + "%";
        this.seasonAnswer = "%" + seasonAnswer + "%";
        this.styleAnswer = "%" + styleAnswer + "%";
    }
}
