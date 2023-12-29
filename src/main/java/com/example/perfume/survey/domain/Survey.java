package com.example.perfume.survey.domain;

import com.example.perfume.perfume.domain.Perfume;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long surveyId;

    @NotNull
    @Column(nullable = false, length = 10)
    private String genderAnswer;

    @NotNull
    @Column(nullable = false, length = 10)
    private String scentAnswer;

    @NotNull
    @Column(nullable = false, length = 10)
    private String moodAnswer;

    @NotNull
    @Column(nullable = false, length = 10)
    private String seasonAnswer;

    @NotNull
    @Column(nullable = false, length = 30)
    private String styleAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Perfume perfume;

    @Builder
    public Survey(Long surveyId, String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer,
                  String styleAnswer, Perfume perfume) {
        this.surveyId = surveyId;
        this.genderAnswer = genderAnswer;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
        this.perfume = perfume;
    }

    public String getMaintenance() {
        return perfume.getMaintenance();
    }
}
