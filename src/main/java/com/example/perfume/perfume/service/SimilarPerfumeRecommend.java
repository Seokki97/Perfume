package com.example.perfume.perfume.service;

import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.SurveyService;
import com.example.perfume.survey.service.SurveyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimilarPerfumeRecommend {
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;
    private final SurveyService surveyService;

    public SimilarPerfumeRecommend(SurveyRepository surveyRepository, SurveyUtil surveyUtil, SurveyService surveyService) {
        this.surveyService = surveyService;
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
    }

    public List<Survey> extractFirstFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByGenderAnswer(surveyService.findSurveyById(perfumeResponseDto.getId()).getGenderAnswer());
    }

    public List<Survey> extractSecondFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByScentAnswer(surveyService.findSurveyById(perfumeResponseDto.getId()).getScentAnswer());
    }

    public List<Survey> extractThirdFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByMoodAnswerContaining(surveyService.findSurveyById(perfumeResponseDto.getId()).getMoodAnswer());
    }

    public List<Survey> addFirstFeatureAndGenderless(PerfumeResponseDto perfumeResponseDto) {
        return surveyUtil.addList(extractFirstFeature(perfumeResponseDto), surveyRepository.findByGenderAnswer("젠더리스"));
    }

    public List<Survey> showSimilarPerfume(PerfumeResponseDto perfumeResponseDto) {
        List<Survey> firstComparedList = surveyUtil.compareTwoFilteredSurveyData
                (addFirstFeatureAndGenderless(perfumeResponseDto), extractSecondFeature(perfumeResponseDto));

        return surveyUtil.compareTwoFilteredSurveyData(firstComparedList, extractThirdFeature(perfumeResponseDto));
    }

}
