package com.example.perfume.perfume.service;

import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.SurveyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimilarPerfumeService {
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;

    public SimilarPerfumeService(SurveyRepository surveyRepository, SurveyUtil surveyUtil) {

        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
    }

    public Survey findFeatureSelectedPerfume(PerfumeResponseDto perfumeResponseDto) {
        Long matchingNumber = perfumeResponseDto.getId();
        return surveyRepository.findById(matchingNumber).get();
    }

    public List<Survey> extractFirstFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByGenderAnswer(findFeatureSelectedPerfume(perfumeResponseDto).getGenderAnswer());
    }

    public List<Survey> extractSecondFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByScentAnswer(findFeatureSelectedPerfume(perfumeResponseDto).getScentAnswer());
    }

    public List<Survey> extractThirdFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByMoodAnswerContaining(findFeatureSelectedPerfume(perfumeResponseDto).getMoodAnswer());
    }

    public List<Survey> addFirstFeatureAndGenderless(PerfumeResponseDto perfumeResponseDto) {
        return surveyUtil.addList(extractFirstFeature(perfumeResponseDto), surveyRepository.findByGenderAnswer("젠더리스"));
    }

    public List<Survey> showSimilarPerfume(PerfumeResponseDto perfumeResponseDto) {
        List<Survey> firstComparedList = surveyUtil.compareTwoFilteredSurveyData
                (addFirstFeatureAndGenderless(perfumeResponseDto), extractSecondFeature(perfumeResponseDto));
        List<Survey> secondComparedList = surveyUtil.compareTwoFilteredSurveyData(firstComparedList, extractThirdFeature(perfumeResponseDto));
        return secondComparedList;
    }

}
