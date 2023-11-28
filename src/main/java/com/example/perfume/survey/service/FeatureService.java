package com.example.perfume.survey.service;

import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.Maintenance;
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
        Survey survey = surveyService.findSurveyById(id);
        return FeatureResponseDto.builder()
                .perfume(survey.getPerfume())
                .scentFeature(ScentType.getFeature(survey))
                .moodFeature(MoodType.getMessage(survey))
                .seasonFeature(SeasonType.getFeature(survey))
                .maintenanceFeature(Maintenance.findMaintenance(survey.getMaintenance()))
                .build();
    }
}
