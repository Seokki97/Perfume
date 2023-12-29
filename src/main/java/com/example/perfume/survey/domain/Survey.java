package com.example.perfume.survey.domain;

import com.example.perfume.perfume.domain.Perfume;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
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
    @Embedded
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private Perfume perfume;

    @Builder
    public Survey(Long surveyId, Question question, Perfume perfume) {
        this.surveyId = surveyId;
        this.question = question;
        this.perfume = perfume;
    }

    public String getMaintenance() {
        return perfume.getMaintenance();
    }

    public String getScentAnswer() {
        return question.getScentAnswer();
    }

    public String getMoodAnswer() {
        return question.getMoodAnswer();
    }

    public String getGenderAnswer() {
        return question.getGenderAnswer();
    }
}
