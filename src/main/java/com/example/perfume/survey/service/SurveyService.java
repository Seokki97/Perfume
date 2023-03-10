package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
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
    public Survey saveSurveyData(Survey survey){
        return surveyRepository.save(survey);
    }

    private List<Survey> isEmptyMoodColumn(SurveyResponseDto surveyResponseDto, List<Survey> secondAnswer) {
        if (secondAnswer.isEmpty()) {
            return retrySecondFiltering(surveyResponseDto);
        }
        return secondAnswer;
    }

    private List<Survey> filterGenderAnswer(SurveyResponseDto surveyResponseDto) {
        if (!surveyRepository.existsByGenderAnswer(surveyResponseDto.getGenderAnswer())) {
            throw new SurveyNotFoundException();
        }
        return surveyRepository.findByGenderAnswer(surveyResponseDto.getGenderAnswer());
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
        return surveyUtil.compareTwoFilteredSurveyData(surveyUtil.addAnswerListByType(filterGenderAnswer(surveyResponseDto), surveyRepository.findByGenderAnswer("????????????"))
                , filterScentAnswer(surveyResponseDto));
    }

    private List<Survey> retrySecondFiltering(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData
                (compareFilteredData(surveyResponseDto), surveyRepository.findByMoodAnswerNotContaining(surveyResponseDto.getMoodAnswer()));
    }

    public List<Perfume> compareData(SurveyResponseDto surveyResponseDto) {
        List<Survey> firstComparedList = compareGenderAndScentAnswer(surveyResponseDto);
        List<Survey> secondComparedList = surveyUtil.compareTwoFilteredSurveyData(firstComparedList, filterMoodAnswer(surveyResponseDto));
        List<Survey> thirdComparedList = surveyUtil.compareTwoFilteredSurveyData(
                isEmptyMoodColumn(surveyResponseDto, secondComparedList),
                surveyUtil.addAnswerListByType(
                        surveyRepository.findBySeasonAnswerContaining(surveyResponseDto.getSeasonAnswer()),
                        surveyRepository.findBySeasonAnswer("??????")));
        List<Survey> finalDataList = surveyUtil.compareTwoFilteredSurveyData(thirdComparedList, surveyUtil.addAnswerListByType(surveyRepository.findByStyleAnswer(
                surveyResponseDto.getStyleAnswer()), surveyRepository.findByStyleAnswer("?????????")));

        return findPerfumeData(finalDataList, thirdComparedList);
    }

    private List<Survey> compareGenderAndScentAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyUtil.addAnswerListByType(
                        filterGenderAnswer(surveyResponseDto),
                        surveyRepository.findByGenderAnswer("????????????")),
                filterScentAnswer(surveyResponseDto));
    }

    private List<Perfume> findPerfumeData(List<Survey> finalDataList, List<Survey> thirdComparedList) {
        return surveyUtil.isEmptyFinalResult(finalDataList, thirdComparedList).stream()
                .map(data -> perfumeService.findPerfumeById(data.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}