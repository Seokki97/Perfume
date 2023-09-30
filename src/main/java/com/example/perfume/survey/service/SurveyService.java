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
        return surveyRepository.findBySurveyId(id).orElseThrow(SurveyNotFoundException::new);
    }

    public Survey saveSurveyData(Survey survey) {
        return surveyRepository.save(survey);
    }

    private List<Survey> filterSurveyResultByQuestion(SurveyRequestDto surveyRequestDto) {
        if (surveyUtil.isNotSelectedSeasonAnswer(surveyRequestDto)) {
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

        if (surveyUtil.isEmptyRecommendedPerfumeList(surveyList)) {
            List<Survey> surveyListByMood = surveyRepository.findSurveysByGenderScentAndMood(
                    surveyRequestDto.getGenderAnswer(), surveyRequestDto.getScentAnswer(), surveyRequestDto.getMoodAnswer());

            return surveyUtil.convertToPerfumeData(surveyListByMood);
        }
        return surveyUtil.convertToPerfumeData(surveyList);
    }

    public List<Perfume> showSimilarPerfumeList(Survey survey) {
        String selectedMoodAnswer = surveyUtil.showMoodAnswer(survey);

        List<Survey> findSimilarData = surveyRepository.findSurveysByGenderScentAndMood
                (survey.getGenderAnswer(), survey.getScentAnswer(), selectedMoodAnswer);

        return surveyUtil.convertToPerfumeData(findSimilarData);
    }

}
