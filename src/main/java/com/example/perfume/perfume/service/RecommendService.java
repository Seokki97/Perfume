package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.SurveyService;
import com.example.perfume.survey.service.SurveyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendService {
    private final PerfumeRepository perfumeRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;


    public RecommendService(PerfumeRepository perfumeRepository, SurveyRepository surveyRepository, SurveyUtil surveyUtil) {
        this.perfumeRepository = perfumeRepository;
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
    }

    public Survey findFeatureSelectedPerfume(PerfumeResponseDto perfumeResponseDto) {
        Long matchingNumber = perfumeResponseDto.getId();
        return surveyRepository.findById(matchingNumber).get();
    }

    public List<Survey> extractFirstFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByFirstAnswerOfSurvey(findFeatureSelectedPerfume(perfumeResponseDto).getFirstAnswerOfSurvey());
    }

    public List<Survey> addFirstFeatureAndGenderless(PerfumeResponseDto perfumeResponseDto) {
        return surveyUtil.addList(extractFirstFeature(perfumeResponseDto), surveyRepository.findByFirstAnswerOfSurvey("젠더리스"));
    }

    public List<Survey> extractSecondFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findBySecondAnswerOfSurvey(findFeatureSelectedPerfume(perfumeResponseDto).getSecondAnswerOfSurvey());
    }

    public List<Survey> extractThirdFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByThirdAnswerOfSurveyContaining(findFeatureSelectedPerfume(perfumeResponseDto).getThirdAnswerOfSurvey());
    }

    public List<Survey> showSimilarPerfume(PerfumeResponseDto perfumeResponseDto) {
        List<Survey> firstComparedList = surveyUtil.compareTwoFilteredSurveyData(addFirstFeatureAndGenderless(perfumeResponseDto), extractSecondFeature(perfumeResponseDto));
        List<Survey> secondComparedList = surveyUtil.compareTwoFilteredSurveyData(firstComparedList, extractThirdFeature(perfumeResponseDto));
        return secondComparedList;
    }


}
