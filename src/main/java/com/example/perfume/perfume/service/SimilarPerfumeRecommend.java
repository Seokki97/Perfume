package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.SurveyService;
import com.example.perfume.survey.service.SurveyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimilarPerfumeRecommend {
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;
    private final SurveyService surveyService;
    private final PerfumeRepository perfumeRepository;

    public SimilarPerfumeRecommend(SurveyRepository surveyRepository, SurveyUtil surveyUtil, SurveyService surveyService,
                                   PerfumeRepository perfumeRepository) {
        this.surveyService = surveyService;
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
        this.perfumeRepository = perfumeRepository;
    }

    private List<Survey> extractFirstFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByGenderAnswer(surveyService.findSurveyById(perfumeResponseDto.getId()).getGenderAnswer());
    }

    private List<Survey> extractSecondFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByScentAnswer(surveyService.findSurveyById(perfumeResponseDto.getId()).getScentAnswer());
    }

    private List<Survey> extractThirdFeature(PerfumeResponseDto perfumeResponseDto) {
        return surveyRepository.findByMoodAnswerContaining(surveyService.findSurveyById(perfumeResponseDto.getId()).getMoodAnswer());
    }

    private List<Survey> addFirstFeatureAndGenderless(PerfumeResponseDto perfumeResponseDto) {
        return surveyUtil.addList(extractFirstFeature(perfumeResponseDto), surveyRepository.findByGenderAnswer("젠더리스"));
    }

    public List<Perfume> showSimilarPerfume(PerfumeResponseDto perfumeResponseDto) {
        List<Survey> firstComparedList = surveyUtil.compareTwoFilteredSurveyData
                (addFirstFeatureAndGenderless(perfumeResponseDto), extractSecondFeature(perfumeResponseDto));
        List<Survey> result = surveyUtil.compareTwoFilteredSurveyData(firstComparedList, extractThirdFeature(perfumeResponseDto));
        List<Perfume> perfumeList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            perfumeList.add(perfumeRepository.findById(result.get(i).getId()).orElseThrow(PerfumeNotFoundException::new));
        }
        return perfumeList;
        //return surveyUtil.compareTwoFilteredSurveyData(firstComparedList, extractThirdFeature(perfumeResponseDto));
    }

}
