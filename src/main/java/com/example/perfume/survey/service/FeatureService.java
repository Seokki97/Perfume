package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.message.MoodMessage;
import com.example.perfume.survey.message.ScentMessage;
import com.example.perfume.survey.message.SeasonMessage;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureService {

    private final SurveyRepository surveyRepository;

    private final PerfumeRepository perfumeRepository;
    private final SurveyService surveyService;

    public FeatureService(SurveyRepository surveyRepository, SurveyService surveyService, PerfumeRepository perfumeRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyService = surveyService;
        this.perfumeRepository = perfumeRepository;
    }

    //구조 : 향수 클릭하면 세부사항 뷰를 보여줘야함 ->향수 클릭하면 향수 ID 반환받아서 해당 향수 디테일 제공, 향수 쓸때 팁 제공
    public Survey findFeature(Long id) {

        return surveyRepository.findById(id).get();
    }

    //그럼 각 무드에 대해 메시지를 맵핑해야하나..?
    public FeatureResponseDto showFeatureDetails(Long id) {
        Survey survey = findFeature(id);
       FeatureResponseDto featureResponseDto = FeatureResponseDto.builder()
                .perfume(selectPerfume(id))
                .scentRecommend(selectScent(id))
                .moodRecommend(survey.getMoodAnswer() + MoodMessage.MOOD_MESSAGE)
                .seasonRecommend(survey.getSeasonAnswer() + SeasonMessage.SEASON_MESSAGE)
                .build();
        return featureResponseDto;
    }
    public Perfume selectPerfume(Long id){
        return perfumeRepository.findById(id).get();
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


}
