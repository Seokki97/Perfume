package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    private static final String GENDERLESS = "젠더리스";
    private static final String DEFAULT = "디폴트";
    private static final String FOUR_SEASON = "무관";
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;
    private final PerfumeService perfumeService;
    private final PerfumeRepository perfumeRepository;

    public SurveyService(SurveyRepository surveyRepository, SurveyUtil surveyUtil, PerfumeService perfumeService,
                         PerfumeRepository perfumeRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
        this.perfumeService = perfumeService;
        this.perfumeRepository = perfumeRepository;
    }

    public Survey findSurveyById(Long id) {
        return surveyRepository.findById(id).orElseThrow(SurveyNotFoundException::new);
    }

    public Survey saveSurveyData(Survey survey) {
        return surveyRepository.save(survey);
    }

    private List<Survey> isEmptyMoodColumn(SurveyResponseDto surveyResponseDto, List<Survey> secondAnswer) {
        if (secondAnswer.isEmpty()) {
            return retrySecondFiltering(surveyResponseDto);
        }
        return secondAnswer;
    }

    private List<Survey> filterScentAnswer(SurveyResponseDto surveyResponseDto) {
        if (!surveyRepository.existsByScentAnswer(surveyResponseDto.getScentAnswer())) {
            throw new SurveyNotFoundException();
        }
        return surveyRepository.findByScentAnswer(surveyResponseDto.getScentAnswer());
    }

    private List<Survey> filterMoodAnswer(SurveyResponseDto surveyResponseDto) {
        if (!surveyRepository.existsByMoodAnswer(surveyResponseDto.getMoodAnswer())) {
            throw new SurveyNotFoundException();
        }
        return surveyRepository.findByMoodAnswerContaining(surveyResponseDto.getMoodAnswer());
    }

    private List<Survey> compareFilteredData(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyRepository.findByGenderAnswerOrGenderAnswer(
                        surveyResponseDto.getGenderAnswer(), GENDERLESS), filterScentAnswer(surveyResponseDto));
    }

    private List<Survey> retrySecondFiltering(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData
                (compareFilteredData(surveyResponseDto), surveyRepository.findByMoodAnswerNotContaining(surveyResponseDto.getMoodAnswer()));
    }

    public List<Perfume> showPerfumeListBySurvey(SurveyResponseDto surveyResponseDto) {
        List<Survey> surveyList = surveyRepository.findByGenderAnswerOrGenderAnswerAndScentAnswer
                (surveyResponseDto.getGenderAnswer(), GENDERLESS, surveyResponseDto.getScentAnswer());
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
                surveyRepository.findBySeasonAnswerContainingOrSeasonAnswer(surveyResponseDto.getSeasonAnswer(), FOUR_SEASON));
    }

    public List<Survey> filterByStyle(SurveyResponseDto surveyResponseDto, List<Survey> surveyList) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyList,
                surveyRepository.findByStyleAnswerContainingOrStyleAnswer(surveyResponseDto.getStyleAnswer(), DEFAULT));

    }


    private List<Perfume> findPerfumeData(List<Survey> finalDataList, List<Survey> thirdComparedList) {
        return surveyUtil.isEmptyFinalResult(finalDataList, thirdComparedList).stream()
                .map(data -> perfumeService.findPerfumeById(data.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}