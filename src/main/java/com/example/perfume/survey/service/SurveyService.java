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

    private SurveyUtil surveyUtil;

    public SurveyService(SurveyRepository surveyRepository, List<Survey> surveyList, SurveyUtil surveyUtil) {
        this.surveyRepository = surveyRepository;
        this.surveyList = surveyList;
        this.surveyUtil = surveyUtil;
    }

    private List<Survey> findDataToStream(List<Survey> testList, List<Survey> surveyList) {
        List<Survey> test = testList.stream().filter(o -> surveyList.stream()
                .anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
        return test;
    }

    private List<Survey> addList(List<Survey> list1, List<Survey> list2) {
        List<Survey> addedList = new ArrayList<>();
        addedList.addAll(list1);
        addedList.addAll(list2);
        return addedList;
    }

    private List<Survey> filterFirstAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyRepository.findByFirstAnswerOfSurvey(surveyResponseDto.getFirstAnswerOfSurvey());
    }

    private List<Survey> filterSecondAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyRepository.findBySecondAnswerOfSurvey(surveyResponseDto.getSecondAnswerOfSurvey());
    }

    private List<Survey> filterThirdAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyRepository.findByThirdAnswerOfSurveyContaining(surveyResponseDto.getThirdAnswerOfSurvey());
    }

    private List<Survey> addFirstAnswerList(SurveyResponseDto surveyResponseDto) {
        return addList(filterFirstAnswer(surveyResponseDto), surveyRepository.findByFirstAnswerOfSurvey("젠더리스"));
    }

    private List<Survey> addFourthAnswerList(SurveyResponseDto surveyResponseDto) {
        return addList(surveyRepository.findByFourthAnswerOfSurveyContaining
                (surveyResponseDto.getFourthAnswerOfSurvey()), surveyRepository.findByFourthAnswerOfSurvey("무관"));
    }

    private List<Survey> addFifthAnswerList(SurveyResponseDto surveyResponseDto) {
        return addList(surveyRepository.findByFifthAnswerOfSurvey(
                surveyResponseDto.getFifthAnswerOfSurvey()), surveyRepository.findByFifthAnswerOfSurvey("디폴트"));
    }

    public List<Survey> findDataFromAnswerTest(SurveyResponseDto surveyResponseDto) {

        List<Survey> firstFilteringList = findDataToStream(addFirstAnswerList(surveyResponseDto), filterSecondAnswer(surveyResponseDto)); //첫번째 두번째 같은거 찾기

        List<Survey> secondFilteringList = findDataToStream(firstFilteringList, filterThirdAnswer(surveyResponseDto));

        List<Survey> thirdFilteringList = findDataToStream(secondFilteringList, addFourthAnswerList(surveyResponseDto));

        List<Survey> filteringListResult = findDataToStream(thirdFilteringList, addFifthAnswerList(surveyResponseDto));
        surveyList = filteringListResult;
        return surveyList;
    }

    public List<Survey> initializeFeatureList() {
        surveyList = new ArrayList<>();
        return surveyList;
    }


}
