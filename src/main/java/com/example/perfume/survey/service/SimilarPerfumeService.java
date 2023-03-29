package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.domain.SurveyType;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimilarPerfumeService {
    private final SurveyService surveyService;

    public SimilarPerfumeService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public List<Perfume> showSimilarPerfume(Long id) {
        Survey survey = surveyService.findSurveyById(id);
        return surveyService.showSimilarPerfumeList(survey);
    }
}
