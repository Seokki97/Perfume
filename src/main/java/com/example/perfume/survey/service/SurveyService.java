package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.domain.SurveyType;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;
    private final PerfumeService perfumeService;

    public SurveyService(SurveyRepository surveyRepository, SurveyUtil surveyUtil, PerfumeService perfumeService) {
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
        this.perfumeService = perfumeService;
    }

    public Survey findSurveyById(Long id) {
        return surveyRepository.findById(id).orElseThrow(SurveyNotFoundException::new);
    }

    public Survey saveSurveyData(Survey survey) {
        return surveyRepository.save(survey);
    }

    public List<Perfume> showPerfumeListBySurvey(SurveyRequestDto surveyRequestDto) {
        List<Survey> surveyList = surveyRepository.findByGenderAnswerOrGenderAnswerAndScentAnswer
                (surveyRequestDto.getGenderAnswer(), SurveyType.GENDERLESS.getValue(), surveyRequestDto.getScentAnswer());
        List<Survey> filteredSurveys = filterByMood(surveyRequestDto, surveyList);
        filteredSurveys = filterBySeason(surveyRequestDto, filteredSurveys);
        filteredSurveys = filterByStyle(surveyRequestDto, filteredSurveys);
        return findPerfumeData(filteredSurveys, surveyList);
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

    private List<Perfume> findPerfumeData(List<Survey> finalDataList, List<Survey> thirdComparedList) {
        return surveyUtil.isEmptyFinalResult(finalDataList, thirdComparedList).stream()
                .map(data -> perfumeService.findPerfumeById(data.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}