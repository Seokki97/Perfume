package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Feature;
import com.example.perfume.survey.dto.featureDto.FeatureDto;
import com.example.perfume.survey.dto.featureDto.FeatureRequestDto;
import com.example.perfume.survey.repository.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SurveyService {

    private final FeatureRepository featureRepository;

    private List<Feature> featureList;

    public SurveyService(FeatureRepository featureRepository, List<Feature> featureList) {
        this.featureRepository = featureRepository;
        this.featureList = featureList;
    }

    public List<Feature> findFirstDataFromQuestionTest(String firstAnswerOfSurvey) {
        featureList = featureRepository.findByFirstAnswerOfSurvey(firstAnswerOfSurvey);
        return featureList;
    }

    public List<Feature> findSecondDataFromAnswer(String secondAnswerOfSurvey) {
        featureList = featureRepository.findBySecondAnswerOfSurveyLike(secondAnswerOfSurvey);
        featureList = featureRepository.findBySecondAnswerOfSurveyLike("무관");
        return featureList;
    }

    public List<Feature> findThirdDataFromAnswer(String thirdAnswerOfSurvey) {
        featureList = featureRepository.findByThirdAnswerOfSurvey(thirdAnswerOfSurvey);
        return featureList;
    }

    public List<Feature> findFourthDataFromAnswer(String fourthAnswerOfSurvey) {
        featureList = featureRepository.findByFourthAnswerOfSurvey(fourthAnswerOfSurvey);
        return featureList;
    }

    public List<Feature> findFifthDataFromAnswer(String fifthAnswerOfSurvey) {
        featureList = featureRepository.findByFifthAnswerOfSurvey(fifthAnswerOfSurvey);
        return featureList;
    }

    public List<Feature> findDataFromAnswerTest(FeatureDto featureDto) {
        findFirstDataFromQuestionTest(featureDto.getFirstAnswerOfSurvey());
        findSecondDataFromAnswer(featureDto.getSecondAnswerOfSurvey());
        findThirdDataFromAnswer(featureDto.getThirdAnswerOfSurvey());
        findFourthDataFromAnswer(featureDto.getFourthAnswerOfSurvey());
        findFifthDataFromAnswer(featureDto.getFifthAnswerOfSurvey());
        return featureList;
    }

    public List<Feature> initializeFeatureList() {
        featureList = new ArrayList<>();
        return featureList;
    }


}
