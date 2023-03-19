package com.example.perfume.survey.service;

import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.MoodType;
import com.example.perfume.survey.domain.ScentType;
import com.example.perfume.survey.domain.SeasonType;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {
    private final SurveyService surveyService;
    private final PerfumeService perfumeService;

    public FeatureService(SurveyService surveyService, PerfumeService perfumeService) {
        this.surveyService = surveyService;
        this.perfumeService = perfumeService;
    }

    public FeatureResponseDto showFeatureDetails(Long id) {
        return  FeatureResponseDto.builder()
                .perfume(perfumeService.findPerfumeById(id))
                .scentRecommend(selectScent(id))
                .moodRecommend(selectMood(id))
                .seasonRecommend(selectSeason(id))
                .build();
    }

    private String selectScent(Long id) {
        Survey survey = surveyService.findSurveyById(id);
        return ScentType.getFeature(survey);
    }

    private String selectSeason(Long id) {
        Survey survey = surveyService.findSurveyById(id);
        return SeasonType.getFeature(survey);
    }

    private String selectMood(Long id) {
        Survey survey = surveyService.findSurveyById(id);
        return MoodType.getMessage(survey);
    }
}
