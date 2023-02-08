package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
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

    public List<Survey> addList(List<Survey> firstDataList, List<Survey> secondDataList) {
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

    private List<Survey> isEmptyFinalResult(List<Survey> finalResult, List<Survey> beforeResult) {
        if (finalResult.isEmpty()) {
            return beforeResult;
        }
        return finalResult;
    }

    private List<Survey> isEmptyMoodColumn(SurveyResponseDto surveyResponseDto, List<Survey> secondAnswer) {
        if (secondAnswer.isEmpty()) {
            //비었으면 그 해당 데이터를 제외한 나머지 데이터를 다시 리스트로 가져와야함.
            return retrySecondFiltering(surveyResponseDto);
        }
        return secondAnswer;
    }

    private List<Survey> retrySecondFiltering(SurveyResponseDto surveyResponseDto) {
        return compareTwoFilteredSurveyData(compareTwoFilteredSurveyData(addFirstAnswerList(surveyResponseDto)
                        , filterSecondAnswer(surveyResponseDto))
                , surveyRepository.findByThirdAnswerOfSurveyNotContaining(surveyResponseDto.getThirdAnswerOfSurvey()));
    }

    public List<Survey> findDataFromAnswerTest(SurveyResponseDto surveyResponseDto) {

        List<Survey> firstFilteringList = compareTwoFilteredSurveyData(addFirstAnswerList(surveyResponseDto), filterSecondAnswer(surveyResponseDto));

        List<Survey> secondFilteringList = compareTwoFilteredSurveyData(firstFilteringList, filterThirdAnswer(surveyResponseDto));

        List<Survey> thirdFilteringList = compareTwoFilteredSurveyData(isEmptyMoodColumn(surveyResponseDto, secondFilteringList), addFourthAnswerList(surveyResponseDto));

        List<Survey> filteringListResult = compareTwoFilteredSurveyData(thirdFilteringList, addFifthAnswerList(surveyResponseDto));
        isEmptyFinalResult(filteringListResult, thirdFilteringList);

        return filteringListResult;
    }

    public List<Survey> extractPerfumeDataFromSurvey(SurveyResponseDto surveyResponseDto) {
        return findDataFromAnswerTest(surveyResponseDto);
    }

    //이제 최종 결과물에 대한 perfume 객체를 리턴해주면 됨
}
