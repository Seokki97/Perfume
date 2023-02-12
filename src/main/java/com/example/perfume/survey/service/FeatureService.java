package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.message.MoodMessage;
import com.example.perfume.survey.message.ScentMessage;
import com.example.perfume.survey.message.SeasonMessage;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {

    private final SurveyRepository surveyRepository;

    private final PerfumeRepository perfumeRepository;

    public FeatureService(SurveyRepository surveyRepository, PerfumeRepository perfumeRepository) {
        this.surveyRepository = surveyRepository;
        this.perfumeRepository = perfumeRepository;
    }

    public Survey findFeature(Long id) {

        return surveyRepository.findById(id).orElseThrow(SurveyNotFoundException::new);
    }

    public FeatureResponseDto showFeatureDetails(Long id) {
        Survey survey = findFeature(id);
        FeatureResponseDto featureResponseDto = FeatureResponseDto.builder()
                .perfume(selectPerfume(id))
                .scentRecommend(selectScent(id))
                .moodRecommend(survey.getMoodAnswer() + MoodMessage.MOOD_MESSAGE)
                .seasonRecommend(selectSeason(id))
                .build();
        return featureResponseDto;
    }

    public Perfume selectPerfume(Long id) {
        return perfumeRepository.findById(id).orElseThrow(PerfumeNotFoundException::new);
    }

    public String selectScent(Long id) {
        Survey survey = findFeature(id);
        String message = "";
        if (survey.getScentAnswer().equals("시트러스")) {
            message = ScentMessage.CITRUS_MESSAGE;
        }
        if (survey.getScentAnswer().equals("소피")) {
            message = ScentMessage.SOAPY_MESSAGE;
        }
        if (survey.getScentAnswer().equals("우디")) {
            message = ScentMessage.WOODY_MESSAGE;
        }
        if (survey.getScentAnswer().equals("플로럴")) {
            message = ScentMessage.FLORAL_MESSAGE;
        }
        if (survey.getScentAnswer().equals("프루티")) {
            message = ScentMessage.FRUITY_MESSAGE;
        }
        if (survey.getScentAnswer().equals("바닐라")) {
            message = ScentMessage.VANILLA_MESSAGE;
        }
        return message;
    }

    public String selectSeason(Long id) {
        Survey survey = findFeature(id);
        if (survey.getSeasonAnswer().equals("무관")) {
            return SeasonMessage.FOUR_SEASONS_MESSAGE;
        }
        return survey.getSeasonAnswer() + SeasonMessage.SEASON_MESSAGE;
    }

}