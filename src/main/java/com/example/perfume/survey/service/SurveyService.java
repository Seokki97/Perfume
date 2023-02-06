package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    private List<Survey> surveyList;

    public SurveyService(SurveyRepository surveyRepository, List<Survey> surveyList) {
        this.surveyRepository = surveyRepository;
        this.surveyList = surveyList;
    }

    public List<Survey> findFirstDataFromQuestionTest(String firstAnswerOfSurvey) {
        surveyList = surveyRepository.findByFirstAnswerOfSurvey(firstAnswerOfSurvey);
        return surveyList;
    }

    public List<Survey> findSecondDataFromAnswer(String secondAnswerOfSurvey) {
        surveyList = surveyRepository.findBySecondAnswerOfSurveyLike(secondAnswerOfSurvey);
        surveyList = surveyRepository.findBySecondAnswerOfSurveyLike("무관");
        return surveyList;
    }

    public List<Survey> findThirdDataFromAnswer(String thirdAnswerOfSurvey) {
        surveyList = surveyRepository.findByThirdAnswerOfSurvey(thirdAnswerOfSurvey);
        return surveyList;
    }

    public List<Survey> findFourthDataFromAnswer(String fourthAnswerOfSurvey) {
        surveyList = surveyRepository.findByFourthAnswerOfSurvey(fourthAnswerOfSurvey);
        return surveyList;
    }

    public List<Survey> findFifthDataFromAnswer(String fifthAnswerOfSurvey) {
        surveyList = surveyRepository.findByFifthAnswerOfSurvey(fifthAnswerOfSurvey);
        return surveyList;
    }

    public List<Survey> findDataFromAnswerTest(SurveyResponseDto surveyResponseDto) {
        findFirstDataFromQuestionTest(surveyResponseDto.getFirstAnswerOfSurvey());
        findSecondDataFromAnswer(surveyResponseDto.getSecondAnswerOfSurvey());
        findThirdDataFromAnswer(surveyResponseDto.getThirdAnswerOfSurvey());
        findFourthDataFromAnswer(surveyResponseDto.getFourthAnswerOfSurvey());
        findFifthDataFromAnswer(surveyResponseDto.getFifthAnswerOfSurvey());
        return surveyList;
    }

    public List<Survey> initializeFeatureList() {
        surveyList = new ArrayList<>();
        return surveyList;
    }


}
