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
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;

    public SimilarPerfumeService(SurveyService surveyService,
                                 SurveyRepository surveyRepository, SurveyUtil surveyUtil) {
        this.surveyService = surveyService;
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
    }

    private List<Survey> findExceptRequestedPerfume(List<Survey> surveyList, SurveyRequestDto surveyRequestDto) {
        return surveyList.stream()
                .filter(x -> x.getId() != surveyRequestDto.getId())
                .collect(Collectors.toList());
    }

    public List<Perfume> showSimilarPerfume(Long id) {
        Survey survey = surveyService.findSurveyById(id);
        List<Survey> filteredSurveyList = surveyRepository.findByGenderAnswerAndScentAnswer(survey.getGenderAnswer(), survey.getScentAnswer());
        filteredSurveyList = isGenderlessPerfume(survey,filteredSurveyList);
        filteredSurveyList = surveyService.filterByMood(surveyUtil.showMoodAnswer(survey), filteredSurveyList);
        filteredSurveyList = findExceptRequestedPerfume(filteredSurveyList, SurveyRequestDto.makeDto(survey));

        return surveyService.convertToPerfumeData(filteredSurveyList);
    }

    public List<Survey> isGenderlessPerfume(Survey survey,List<Survey> filteredSurveyList){
        if(!survey.getGenderAnswer().matches(SurveyType.GENDERLESS.getValue())){
            filteredSurveyList = surveyUtil.addList(filteredSurveyList, surveyRepository.findByGenderAnswerAndScentAnswer(SurveyType.GENDERLESS.getValue(), survey.getScentAnswer()));
        }
        return filteredSurveyList;
    }
}
