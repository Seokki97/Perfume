package com.example.perfume.survey.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    private List<Survey> surveyList;

    public SurveyService(SurveyRepository surveyRepository, List<Survey> surveyList) {
        this.surveyRepository = surveyRepository;
        this.surveyList = surveyList;
    }

    public List<Survey> findFirstDataFromQuestionTest(String firstAnswerOfSurvey) {
        surveyRepository.findByFirstAnswerOfSurvey(firstAnswerOfSurvey);
        surveyRepository.findByFirstAnswerOfSurvey("젠더리스");
        return surveyList;
    }

    public List<Survey> findSecondDataFromAnswer(String secondAnswerOfSurvey) {
        surveyRepository.findBySecondAnswerOfSurvey(secondAnswerOfSurvey);
        return surveyList;
    }

    public List<Survey> findThirdDataFromAnswer(String thirdAnswerOfSurvey) {
        surveyRepository.findByThirdAnswerOfSurveyLike(thirdAnswerOfSurvey);
        return surveyList;
    }

    public List<Survey> findFourthDataFromAnswer(String fourthAnswerOfSurvey) {
        surveyRepository.findByFourthAnswerOfSurveyLike(fourthAnswerOfSurvey);
        surveyRepository.findByFourthAnswerOfSurveyLike("무관");

        return surveyList;
    }

    public List<Survey> findFifthDataFromAnswer(String fifthAnswerOfSurvey) {
        surveyRepository.findByFifthAnswerOfSurvey(fifthAnswerOfSurvey);
        surveyRepository.findByFifthAnswerOfSurvey("디폴트");
        return surveyList;
    }

    public List<Survey> findDataFromAnswerTest(SurveyResponseDto surveyResponseDto) {
        List<Survey> first = surveyRepository.findByFirstAnswerOfSurvey(surveyResponseDto.getFirstAnswerOfSurvey());
        List<Survey> second = surveyRepository.findBySecondAnswerOfSurvey(surveyResponseDto.getSecondAnswerOfSurvey());
        List<Survey> test = first.stream().filter(o ->second.stream()
                .anyMatch(Predicate.isEqual(o))).collect(Collectors.toList()); //첫번째 두번째 같은거 찾기

        List<Survey> third = surveyRepository.findByThirdAnswerOfSurveyLike(surveyResponseDto.getThirdAnswerOfSurvey());

        List<Survey> test2 = test.stream().filter(o -> third.stream()
                .anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());

        List<Survey> fourth = surveyRepository.findByFourthAnswerOfSurveyLike(surveyResponseDto.getFourthAnswerOfSurvey());

        List<Survey> test3 = test2.stream().filter(o -> fourth.stream()
                .anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());

        List<Survey> fifth = surveyRepository.findByFifthAnswerOfSurvey(surveyResponseDto.getFifthAnswerOfSurvey());

        List<Survey> test4 = test3.stream().filter(o -> fifth.stream()
                .anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());

        surveyList = test4;
        return surveyList;
    }

    public List<Survey> showAllData() {
        return surveyList;
    }

    public List<Survey> initializeFeatureList() {
        surveyList = new ArrayList<>();
        return surveyList;
    }


}
