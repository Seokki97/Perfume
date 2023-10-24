package com.example.perfume.survey.service;

import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.*;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {

    private final SurveyService surveyService;
    private final PerfumeService perfumeService;
    private final SurveyUtil surveyUtil;

    public FeatureService(SurveyService surveyService, PerfumeService perfumeService, SurveyUtil surveyUtil) {
        this.surveyService = surveyService;
        this.perfumeService = perfumeService;
        this.surveyUtil = surveyUtil;
    }

    public FeatureResponseDto showFeatureDetails(Long id) {
        return FeatureResponseDto.builder()
                .perfume(perfumeService.findPerfumeById(id))
                .scentRecommend(selectScent(id))
                .moodRecommend(selectMood(id))
                .seasonRecommend(selectSeason(id))
                .maintenanceRecommend(selectMaintenance(id))
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
        Survey surveyId = surveyService.findSurveyById(id);
        surveyUtil.showMoodAnswer(surveyService.findSurveyById(id));

        Survey survey = Survey.builder()
                .moodAnswer(surveyUtil.showMoodAnswer(surveyId))
                .build();
        return MoodType.getMessage(survey);
    }

    private String selectMaintenance(Long id) {
        String maintenanceMessage = surveyService.findSurveyById(id).getPerfume().getMaintenance();
        return Maintenance.findMaintenance(maintenanceMessage);
    }
}
