package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    private List<Survey> compareTwoFilteredSurveyData(List<Survey> firstDataList, List<Survey> secondDataList) {

        return firstDataList.stream()
                .filter(o -> secondDataList.stream().anyMatch(Predicate.isEqual(o)))
                .collect(Collectors.toList());
    }

    private List<Survey> addList(List<Survey> firstDataList, List<Survey> secondDataList) {
        List<Survey> addedList = new ArrayList<>();
        addedList.addAll(firstDataList);
        addedList.addAll(secondDataList);
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

        List<Survey> firstFilteringList = compareTwoFilteredSurveyData(addFirstAnswerList(surveyResponseDto), filterSecondAnswer(surveyResponseDto)); //첫번째 두번째 같은거 찾기

        List<Survey> secondFilteringList = compareTwoFilteredSurveyData(firstFilteringList, filterThirdAnswer(surveyResponseDto));

        List<Survey> thirdFilteringList = compareTwoFilteredSurveyData(secondFilteringList, addFourthAnswerList(surveyResponseDto));

        List<Survey> filteringListResult = compareTwoFilteredSurveyData(thirdFilteringList, addFifthAnswerList(surveyResponseDto));

        return filteringListResult;
    }



}
