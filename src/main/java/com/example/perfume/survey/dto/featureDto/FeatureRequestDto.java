package com.example.perfume.survey.dto.featureDto;

import com.example.perfume.survey.domain.Feature;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Table;
import java.util.List;

@Getter
@Table(name = "feature")
public class FeatureRequestDto {

    private Long id;

    private String firstAnswerOfSurvey;
    private String secondAnswerOfSurvey;
    private String thirdAnswerOfSurvey;
    private String fourthAnswerOfSurvey;
    private String fifthAnswerOfSurvey;


    // public Long perfumeId;

    public FeatureRequestDto(Long id, String firstAnswerOfSurvey, String secondAnswerOfSurvey, String thirdAnswerOfSurvey, String fourthAnswerOfSurvey, String fifthAnswerOfSurvey) {
        this.id = id;
        this.firstAnswerOfSurvey = firstAnswerOfSurvey;
        this.secondAnswerOfSurvey = secondAnswerOfSurvey;
        this.thirdAnswerOfSurvey = thirdAnswerOfSurvey;
        this.fourthAnswerOfSurvey = fourthAnswerOfSurvey;
        this.fifthAnswerOfSurvey = fifthAnswerOfSurvey;
    }


    public Feature toEntity() {
        return Feature.builder()
                .id(id)
                .firstAnswerOfSurvey(firstAnswerOfSurvey)
                .secondAnswerOfSurvey(secondAnswerOfSurvey)
                .thirdAnswerOfSurvey(thirdAnswerOfSurvey)
                .fourthAnswerOfSurvey(fourthAnswerOfSurvey)
                .fifthAnswerOfSurvey(fifthAnswerOfSurvey)
                .build();
    }
}
