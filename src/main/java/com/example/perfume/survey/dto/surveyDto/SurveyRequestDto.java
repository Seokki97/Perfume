package com.example.perfume.survey.dto.surveyDto;


import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SurveyRequestDto {

    private String genderAnswer;
    private String scentAnswer;
    private String moodAnswer;
    private String seasonAnswer;
    private String styleAnswer;

    public SurveyRequestDto() {

    }

    @Builder
    public SurveyRequestDto(String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer) {
        this.genderAnswer = genderAnswer;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
    }
}
