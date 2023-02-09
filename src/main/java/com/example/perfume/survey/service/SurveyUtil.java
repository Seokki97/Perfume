package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SurveyUtil {

    private final SurveyRepository surveyRepository;

    public SurveyUtil(SurveyRepository surveyRepository){
        this.surveyRepository = surveyRepository;
    }
    public List<Survey> compareTwoFilteredSurveyData(List<Survey> firstDataList, List<Survey> secondDataList) {
        return firstDataList.stream()
                .filter(o -> secondDataList.stream().anyMatch(Predicate.isEqual(o)))
                .collect(Collectors.toList());
    }

    public List<Survey> addList(List<Survey> firstDataList, List<Survey> secondDataList) {
        List<Survey> addedList = new ArrayList<>();
        addedList.addAll(firstDataList);
        addedList.addAll(secondDataList);
        return addedList;
    }

    public List<Survey> filterFirstAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyRepository.findByFirstAnswerOfSurvey(surveyResponseDto.getFirstAnswerOfSurvey());
    }

    public List<Survey> filterSecondAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyRepository.findBySecondAnswerOfSurvey(surveyResponseDto.getSecondAnswerOfSurvey());
    }

    public List<Survey> filterThirdAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyRepository.findByThirdAnswerOfSurveyContaining(surveyResponseDto.getThirdAnswerOfSurvey());
    }
}
