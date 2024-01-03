package com.example.perfume.survey.dto.surveyDto;


import com.example.perfume.survey.domain.Question;
import lombok.Getter;

@Getter
public class SurveyRequestDto {

    private Question question;

    public SurveyRequestDto() {

    }

    public SurveyRequestDto(Question question) {
        this.question = question;
    }

    public String getScentAnswer() {
        return question.getScentAnswer();
    }

    public String getMoodAnswer() {
        return question.getMoodAnswer();
    }

    public String getStyleAnswer() {
        return question.getStyleAnswer();
    }

    public String getSeasonAnswer() {
        return question.getSeasonAnswer();
    }

    public String getGenderAnswer() {
        return question.getGenderAnswer();
    }
}
