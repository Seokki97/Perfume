package com.example.perfume.crawling.domain.survey;

import lombok.Builder;

import java.util.List;

@Builder
public class SurveyList {

    private List<String> firstAnswer;

    private List<String> secondAnswer;

    private List<String> thirdAnswer;

    private List<String> fourthAnswer;

    private List<String> fifthAnswer;


    public SurveyList(List<String> firstAnswer,List<String> secondAnswer,List<String> thirdAnswer, List<String> fourthAnswer, List<String> fifthAnswer){
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.fifthAnswer = fifthAnswer;
    }
}
