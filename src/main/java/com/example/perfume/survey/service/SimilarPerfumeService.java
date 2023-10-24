package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SimilarPerfumeService {

    private final SurveyService surveyService;

    public SimilarPerfumeService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public List<Perfume> showSimilarPerfume(Long id) {
        Survey survey = surveyService.findSurveyById(id);
        return surveyService.showSimilarPerfumeList(survey).stream()
                .filter(perfume -> !Objects.equals(id, perfume.getPerfumeId())).collect(Collectors.toList());
    }
}
