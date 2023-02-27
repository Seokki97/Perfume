package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;
    private final PerfumeRepository perfumeRepository;

    public SurveyService(SurveyRepository surveyRepository, SurveyUtil surveyUtil,
                         PerfumeRepository perfumeRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
        this.perfumeRepository = perfumeRepository;
    }

    public List<Survey> addGenderAnswerList(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.addList(surveyUtil.filterGenderAnswer(surveyResponseDto), surveyRepository.findByGenderAnswer("젠더리스"));
    }

    private List<Survey> addSeasonAnswerList(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.addList(surveyRepository.findBySeasonAnswerContaining
                (surveyResponseDto.getSeasonAnswer()), surveyRepository.findBySeasonAnswer("무관"));
    }

    private List<Survey> addStyleAnswerList(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.addList(surveyRepository.findByStyleAnswer(
                surveyResponseDto.getStyleAnswer()), surveyRepository.findByStyleAnswer("디폴트"));
    }

    private List<Survey> isEmptyFinalResult(List<Survey> finalResult, List<Survey> beforeResult) {
        if (finalResult.isEmpty()) {
            return beforeResult;
        }
        return finalResult;
    }

    private List<Survey> isEmptyMoodColumn(SurveyResponseDto surveyResponseDto, List<Survey> secondAnswer) {
        if (secondAnswer.isEmpty()) {
            //비었으면 그 해당 데이터를 제외한 나머지 데이터를 다시 리스트로 가져와야함.
            return retrySecondFiltering(surveyResponseDto);
        }
        return secondAnswer;
    }

    private List<Survey> compareFilteredData(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData(addGenderAnswerList(surveyResponseDto)
                , surveyUtil.filterScentAnswer(surveyResponseDto));
    }

    private List<Survey> retrySecondFiltering(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData
                (compareFilteredData(surveyResponseDto), surveyRepository.findByMoodAnswerNotContaining(surveyResponseDto.getMoodAnswer()));
    }

    public List<Perfume> compareData(SurveyResponseDto surveyResponseDto) {
        List<Survey> firstComparedList = surveyUtil.compareTwoFilteredSurveyData(addGenderAnswerList(surveyResponseDto), surveyUtil.filterScentAnswer(surveyResponseDto));
        List<Survey> secondComparedList = surveyUtil.compareTwoFilteredSurveyData(firstComparedList, surveyUtil.filterMoodAnswer(surveyResponseDto));
        List<Survey> thirdComparedList = surveyUtil.compareTwoFilteredSurveyData(isEmptyMoodColumn(surveyResponseDto, secondComparedList), addSeasonAnswerList(surveyResponseDto));
        List<Survey> finalDataList = surveyUtil.compareTwoFilteredSurveyData(thirdComparedList, addStyleAnswerList(surveyResponseDto));

        return findPerfumeData(finalDataList, thirdComparedList);
    }

    public List<Perfume> findPerfumeData(List<Survey> finalDataList, List<Survey> thirdComparedList) {
        List<Perfume> perfumeList = new ArrayList<>();
        for (int i = 0; i < isEmptyFinalResult(finalDataList, thirdComparedList).size(); i++) {
            Long perfumeId = isEmptyFinalResult(finalDataList, thirdComparedList).get(i).getId();
            perfumeList.add(perfumeRepository.findById(perfumeId).get());
        }
        return perfumeList;
    }

}