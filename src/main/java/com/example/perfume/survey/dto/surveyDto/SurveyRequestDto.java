package com.example.perfume.survey.dto.surveyDto;


import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SurveyRequestDto {

    private Long id;
    private String genderAnswer;
    private String scentAnswer;
    private String moodAnswer;
    private String seasonAnswer;
    private String styleAnswer;

    public SurveyRequestDto() {

    }

    @Builder
    public SurveyRequestDto(Long id, String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer) {
        this.id = id;
        this.genderAnswer = genderAnswer;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;

    }
    public static SurveyRequestDto makeDto(Survey survey){
        return  SurveyRequestDto.builder().id(survey.getId())
                .genderAnswer(survey.getGenderAnswer())
                .scentAnswer(survey.getScentAnswer())
                .seasonAnswer(survey.getSeasonAnswer())
                .moodAnswer(survey.getMoodAnswer())
                .styleAnswer(survey.getStyleAnswer())
                .build();
    }
    public Survey toEntity(Perfume perfume) {
        return Survey.builder()
                .id(id)
                .genderAnswer(genderAnswer)
                .scentAnswer(scentAnswer)
                .moodAnswer(moodAnswer)
                .seasonAnswer(seasonAnswer)
                .styleAnswer(styleAnswer)
                .build();
    }
}
