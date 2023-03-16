package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.domain.SurveyType;
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

    public List<Perfume> showPerfumeListBySurvey(SurveyResponseDto surveyResponseDto) {
        List<Survey> surveyList = surveyRepository.findByGenderAnswerOrGenderAnswerAndScentAnswer
                (surveyResponseDto.getGenderAnswer(), SurveyType.GENDERLESS.getValue(), surveyResponseDto.getScentAnswer());
        List<Survey> filteredSurveys = filterByMood(surveyResponseDto, surveyList);
        filteredSurveys = filterBySeason(surveyResponseDto, filteredSurveys);
        filteredSurveys = filterByStyle(surveyResponseDto, filteredSurveys);
        return findPerfumeData(filteredSurveys, surveyList);
    }

    public List<Survey> filterByMood(SurveyResponseDto surveyResponseDto, List<Survey> surveyList) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyList,
                surveyRepository.findByMoodAnswerContaining(surveyResponseDto.getMoodAnswer()));
    }

    public List<Survey> filterBySeason(SurveyResponseDto surveyResponseDto, List<Survey> surveyList) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyList,
                surveyRepository.findBySeasonAnswerContainingOrSeasonAnswer(surveyResponseDto.getSeasonAnswer(), SurveyType.FOUR_SEASON.getValue()));
    }

    public List<Survey> filterByStyle(SurveyResponseDto surveyResponseDto, List<Survey> surveyList) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyList,
                surveyRepository.findByStyleAnswerContainingOrStyleAnswer(surveyResponseDto.getStyleAnswer(), SurveyType.DEFAULT.getValue()));

    }

    private List<Perfume> findPerfumeData(List<Survey> finalDataList, List<Survey> thirdComparedList) {
        return surveyUtil.isEmptyFinalResult(finalDataList, thirdComparedList).stream()
                .map(data -> perfumeService.findPerfumeById(data.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}