package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;

    public SurveyService(SurveyRepository surveyRepository, SurveyUtil surveyUtil) {
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
    }

    public List<Survey> addFirstAnswerList(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.addList(surveyUtil.filterFirstAnswer(surveyResponseDto), surveyRepository.findByGenderAnswer("젠더리스"));
    }

    private List<Survey> addFourthAnswerList(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.addList(surveyRepository.findBySeasonAnswerContaining
                (surveyResponseDto.getSeasonAnswer()), surveyRepository.findBySeasonAnswer("무관"));
    }

    private List<Survey> addFifthAnswerList(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.addList(surveyRepository.findByStyleAnswer(
                surveyResponseDto.getStyleAnswer()), surveyRepository.findByStyleAnswer("디폴트"));
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

    private List<Survey> compareFirstAndSecondData(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData(addFirstAnswerList(surveyResponseDto)
                , surveyUtil.filterSecondAnswer(surveyResponseDto));
    }

    private List<Survey> retrySecondFiltering(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData
                (compareFirstAndSecondData(surveyResponseDto), surveyRepository.findByMoodAnswerNotContaining(surveyResponseDto.getMoodAnswer()));
    }

    public List<Survey> compareData(SurveyResponseDto surveyResponseDto) {
        List<Survey> firstComparedList = surveyUtil.compareTwoFilteredSurveyData(addFirstAnswerList(surveyResponseDto), surveyUtil.filterSecondAnswer(surveyResponseDto));
        List<Survey> secondComparedList = surveyUtil.compareTwoFilteredSurveyData(firstComparedList, surveyUtil.filterThirdAnswer(surveyResponseDto));
        List<Survey> thirdComparedList = surveyUtil.compareTwoFilteredSurveyData(isEmptyMoodColumn(surveyResponseDto, secondComparedList), addFourthAnswerList(surveyResponseDto));
        List<Survey> finalDataList = surveyUtil.compareTwoFilteredSurveyData(thirdComparedList, addFifthAnswerList(surveyResponseDto));
        return isEmptyFinalResult(finalDataList, thirdComparedList);
    }

}