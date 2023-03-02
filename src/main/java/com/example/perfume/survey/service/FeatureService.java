package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.ScentType;
import com.example.perfume.survey.domain.SeasonType;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.message.MoodMessage;
import com.example.perfume.survey.repository.SurveyRepository;
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
        FeatureResponseDto featureResponseDto = FeatureResponseDto.builder()
                .perfume(perfumeService.findPerfumeById(id))
                .scentRecommend(selectScent(id))
                .moodRecommend(survey.getMoodAnswer() + MoodMessage.MOOD_MESSAGE)
                .seasonRecommend(selectSeason(id))
                .build();
        return featureResponseDto;
    }


    public String selectScent(Long id) {
        Survey survey = surveyService.findSurveyById(id);
        return ScentType.getFeature(survey);
    }

    public String selectSeason(Long id) {
        Survey survey = surveyService.findSurveyById(id);

        return SeasonType.getFeature(survey);
    }
}
