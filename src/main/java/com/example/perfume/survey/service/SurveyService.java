package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Feature;
import com.example.perfume.survey.dto.featureDto.FeatureDto;
import com.example.perfume.survey.dto.featureDto.FeatureRequestDto;
import com.example.perfume.survey.repository.FeatureRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;


@Service
public class SurveyService {

    private final FeatureRepository featureRepository;

    private final SurveyUtil surveyUtil;

    private List<Feature> featureList;

    public SurveyService(FeatureRepository featureRepository, SurveyUtil surveyUtil, List<Feature> featureList) {
        this.featureRepository = featureRepository;
        this.surveyUtil = surveyUtil;
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

    public void saveAllData(Long id, FeatureRequestDto featureRequestDto) {

        Feature feature = new Feature(id, featureRequestDto.getFirstAnswerOfSurvey(), featureRequestDto.getSecondAnswerOfSurvey(),
                featureRequestDto.getThirdAnswerOfSurvey(), featureRequestDto.getFourthAnswerOfSurvey(), featureRequestDto.getFifthAnswerOfSurvey());
        featureRepository.save(feature);
    }


}
