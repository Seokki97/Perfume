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

    public List<Survey> findDataToStream(List<Survey> testList, List<Survey> surveyList){
        List<Survey> test = testList.stream().filter(o -> surveyList.stream()
                .anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
        return test;
    }

    public List<Survey> addList(List<Survey> list1, List<Survey> list2){
        List<Survey> addedList = new ArrayList<>();
        addedList.addAll(list1);
        addedList.addAll(list2);
        return addedList;
    }

    public List<Survey> findDataFromAnswerTest(SurveyResponseDto surveyResponseDto) {
        List<Survey> firstTest = addList(surveyRepository.findByFirstAnswerOfSurvey(surveyResponseDto.getFirstAnswerOfSurvey()),surveyRepository.findByFirstAnswerOfSurvey("젠더리스")); // 두개 더함

        List<Survey> second = surveyRepository.findBySecondAnswerOfSurvey(surveyResponseDto.getSecondAnswerOfSurvey());
        List<Survey> test = findDataToStream(firstTest,second); //첫번째 두번째 같은거 찾기

        List<Survey> third = surveyRepository.findByThirdAnswerOfSurveyContaining(surveyResponseDto.getThirdAnswerOfSurvey());
        List<Survey> test2 = findDataToStream(test,third);

        List<Survey> fourth = surveyRepository.findByFourthAnswerOfSurveyContaining(surveyResponseDto.getFourthAnswerOfSurvey());
        List<Survey> fourth1 = surveyRepository.findByFourthAnswerOfSurvey("무관");
        List<Survey> fourthTest = addList(fourth,fourth1);
        List<Survey> test3 = findDataToStream(test2, fourthTest);

        List<Survey> fifth = surveyRepository.findByFifthAnswerOfSurvey(surveyResponseDto.getFifthAnswerOfSurvey());
        List<Survey> fifth1 = surveyRepository.findByFifthAnswerOfSurvey("디폴트");
        List<Survey> fifthTest = addList(fifth,fifth1);

        List<Survey> test4 = findDataToStream(test3, fifthTest);
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
