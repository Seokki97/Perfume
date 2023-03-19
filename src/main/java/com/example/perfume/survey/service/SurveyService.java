package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.domain.SurveyType;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;


    public SurveyService(SurveyRepository surveyRepository, SurveyUtil surveyUtil) {
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;

    }

    public Survey findSurveyById(Long id) {
        return surveyRepository.findById(id).orElseThrow(SurveyNotFoundException::new);
    }

    public Survey saveSurveyData(Survey survey) {
        return surveyRepository.save(survey);
    }

    public List<Survey> filterByMood(SurveyRequestDto surveyRequestDto, List<Survey> surveyList) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyList,
                surveyRepository.findByMoodAnswerContaining(surveyRequestDto.getMoodAnswer()));
    }

    public List<Survey> filterBySeason(SurveyRequestDto surveyRequestDto, List<Survey> surveyList) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyList,
                surveyRepository.findBySeasonAnswerContainingOrSeasonAnswer(surveyRequestDto.getSeasonAnswer(), SurveyType.FOUR_SEASON.getValue()));
    }

    public List<Survey> filterByStyle(SurveyRequestDto surveyRequestDto, List<Survey> surveyList) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyList,
                surveyRepository.findByStyleAnswerContainingOrStyleAnswer(surveyRequestDto.getStyleAnswer(), SurveyType.DEFAULT.getValue()));
    }

    public List<Survey> filterByGenderAndScent(String gender, String genderless, String scent) {
        return surveyRepository.findByGenderAnswerOrGenderAnswerAndScentAnswer(gender, genderless, scent);
    }

    public List<Perfume> convertToPerfumeData(List<Survey> surveyList) {
        return surveyList.stream()
                .map(data -> data.getPerfume()).collect(Collectors.toList());
    }

    public List<Perfume> showPerfumeListBySurvey(SurveyRequestDto surveyRequestDto) {
        List<Survey> surveyList = filterByGenderAndScent(surveyRequestDto.getGenderAnswer(), SurveyType.GENDERLESS.getValue(), surveyRequestDto.getScentAnswer());
        List<Survey> filteredSurveys = filterByMood(surveyRequestDto, surveyList);
        filteredSurveys = filterBySeason(surveyRequestDto, filteredSurveys);
        List<Survey> filteredBySeasonList = filteredSurveys;
        filteredSurveys = filterByStyle(surveyRequestDto, filteredSurveys);

        return convertToPerfumeData(surveyUtil.isEmptyFinalResult(filteredSurveys, filteredBySeasonList));
    }


}
