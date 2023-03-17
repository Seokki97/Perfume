package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.domain.SurveyType;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimilarPerfumeService {

    private final SurveyService surveyService;

    public SimilarPerfumeService(SurveyService surveyService){
        this.surveyService = surveyService;
    }
    public List<Perfume> showSimilarPerfume(PerfumeRequestDto perfumeRequestDto) {
        Survey survey = surveyService.findSurveyById(perfumeRequestDto.getId());
        List<Survey> surveyList = surveyService.filterByGenderAndScent(survey.getGenderAnswer(), SurveyType.GENDERLESS.getValue(),survey.getScentAnswer());
        List<Survey> filteredSurveys = surveyService.filterByMood(SurveyRequestDto.makeDto(survey), surveyList);
        return surveyService.convertToPerfumeData(findExceptRequestedPerfume(filteredSurveys,SurveyRequestDto.makeDto(survey)));
    }

    private List<Survey> findExceptRequestedPerfume(List<Survey> surveyList, SurveyRequestDto surveyRequestDto) {
        return surveyList.stream()
                .filter(x -> x.getId() != surveyRequestDto.getId())
                .collect(Collectors.toList());
    }
}
