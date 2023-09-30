package com.example.perfume.survey.dto.surveyDto;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SurveyResponseDto {

    private Long surveyId;

    private String genderAnswer;
    private String scentAnswer;
    private String moodAnswer;
    private String seasonAnswer;
    private String styleAnswer;

    private Perfume perfume;

    public SurveyResponseDto() {
    }

    @Builder
    public SurveyResponseDto(Long surveyId, String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer, Perfume perfume) {
        this.surveyId = surveyId;
        this.genderAnswer = genderAnswer;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
        this.perfume = perfume;
    }

    public Survey toEntity(Perfume perfume) {
        return Survey.builder()
                .surveyId(surveyId)
                .genderAnswer(genderAnswer)
                .scentAnswer(scentAnswer)
                .moodAnswer(moodAnswer)
                .seasonAnswer(seasonAnswer)
                .styleAnswer(styleAnswer)
                .perfume(perfume)
                .build();
    }

    public static SurveyResponseDto makeList(Long id, int firstIndex, SurveyList surveyList) {
        return SurveyResponseDto.builder()
                .surveyId(id)
                .genderAnswer(surveyList.getFirstAnswer().get(firstIndex))
                .scentAnswer(surveyList.getSecondAnswer().get(firstIndex))
                .moodAnswer(surveyList.getThirdAnswer().get(firstIndex))
                .seasonAnswer(surveyList.getFourthAnswer().get(firstIndex))
                .styleAnswer(surveyList.getFifthAnswer().get(firstIndex))
                .build();
    }
}
