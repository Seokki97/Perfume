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

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey findSurveyById(Long id) {
        return surveyRepository.findById(id).orElseThrow(SurveyNotFoundException::new);
    }

    public Survey saveSurveyData(Survey survey) {
        return surveyRepository.save(survey);
    }

    public List<Perfume> convertToPerfumeData(List<Survey> surveyList) {
        return surveyList.stream()
                .map(data -> data.getPerfume()).collect(Collectors.toList());
    }

    public List<Survey> filterSurveyResultByQuestion(SurveyRequestDto surveyRequestDto) {
        if (isNotSelectedSeasonAnswer(surveyRequestDto)) {
            return surveyRepository.findSurveysByGenderScentMoodAndStyle(
                    surveyRequestDto.getGenderAnswer(), surveyRequestDto.getScentAnswer(),
                    surveyRequestDto.getMoodAnswer(), surveyRequestDto.getStyleAnswer());
        }
        return surveyRepository.findSurveysByAnswers(
                surveyRequestDto.getGenderAnswer(), surveyRequestDto.getScentAnswer(),
                surveyRequestDto.getMoodAnswer(), surveyRequestDto.getSeasonAnswer(),
                surveyRequestDto.getStyleAnswer());
    }

    public List<Perfume> showPerfumeListBySurvey(SurveyRequestDto surveyRequestDto) {
        List<Survey> surveyList = filterSurveyResultByQuestion(surveyRequestDto);

        if (isEmptyRecommendedPerfumeList(surveyList)) {
            List<Survey> surveyListByMood = surveyRepository.findSurveysByGenderScentAndMood(
                    surveyRequestDto.getGenderAnswer(), surveyRequestDto.getScentAnswer(), surveyRequestDto.getMoodAnswer());
            return convertToPerfumeData(surveyListByMood);
        }
        return convertToPerfumeData(surveyList);
    }

    public List<Perfume> showSimilarPerfumeList(Survey survey) {
        List<Survey> findSimilarData = surveyRepository.findSurveysByGenderScentAndMood
                (survey.getGenderAnswer(), survey.getScentAnswer(), survey.getMoodAnswer());

        return convertToPerfumeData(findSimilarData);
    }

    public boolean isEmptyRecommendedPerfumeList(List<Survey> surveyList) {
        return convertToPerfumeData(surveyList).isEmpty();
    }

    public boolean isNotSelectedSeasonAnswer(SurveyRequestDto surveyRequestDto) {
        return surveyRequestDto.getSeasonAnswer().equals(SurveyType.NOT_SELECT_SEASON.getValue());
    }
}
