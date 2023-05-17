package com.example.perfume.crawling.domain.survey;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyList {

    private List<String> firstAnswer;

    private List<String> secondAnswer;

    private List<String> thirdAnswer;

    private List<String> fourthAnswer;

    private List<String> fifthAnswer;

    @Builder
    public SurveyList(List<String> firstAnswer,List<String> secondAnswer,List<String> thirdAnswer, List<String> fourthAnswer, List<String> fifthAnswer){
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.fifthAnswer = fifthAnswer;
    }

    public int getMaxSize(){
        return fifthAnswer.size();
    }

}
