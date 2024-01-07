package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SimilarPerfumeService {

    private final SurveyUtil surveyUtil;
    private final SurveyRepository surveyRepository;
    private final SurveyConverter surveyConverter;

    public SimilarPerfumeService(SurveyUtil surveyUtil, SurveyRepository surveyRepository,
                                 SurveyConverter surveyConverter) {
        this.surveyUtil = surveyUtil;
        this.surveyRepository = surveyRepository;
        this.surveyConverter = surveyConverter;
    }

    public List<Perfume> showSimilarPerfumeList(Survey survey) {
        String selectedMoodAnswer = surveyUtil.showMoodAnswer(survey);
        List<Survey> findSimilarData = surveyRepository.findSurveysByGenderScentAndMood
                (survey.getGenderAnswer(), survey.getScentAnswer(), selectedMoodAnswer);

        return surveyConverter.convertToPerfumeData(findSimilarData);
    }

    public List<Perfume> showSimilarPerfume(Long id) {
        Survey survey = surveyRepository.findBySurveyId(id)
                .orElseThrow(SurveyNotFoundException::new);
        return showSimilarPerfumeList(survey).stream()
                .filter(perfume -> !Objects.equals(id, perfume.getPerfumeId())).collect(Collectors.toList());
    }
}
