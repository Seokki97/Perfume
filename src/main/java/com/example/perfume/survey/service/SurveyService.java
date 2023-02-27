package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
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

    private List<Survey> isEmptyMoodColumn(SurveyResponseDto surveyResponseDto, List<Survey> secondAnswer) {
        if (secondAnswer.isEmpty()) {
            //비었으면 그 해당 데이터를 제외한 나머지 데이터를 다시 리스트로 가져와야함.
            return retrySecondFiltering(surveyResponseDto);
        }
        return secondAnswer;
    }

    public List<Survey> filterGenderAnswer(SurveyResponseDto surveyResponseDto) {
        if (!surveyRepository.existsByGenderAnswer(surveyResponseDto.getGenderAnswer())) {
            throw new SurveyNotFoundException();
        }
        return surveyRepository.findByGenderAnswer(surveyResponseDto.getGenderAnswer());
    }

    public List<Survey> filterScentAnswer(SurveyResponseDto surveyResponseDto) {
        if (!surveyRepository.existsByScentAnswer(surveyResponseDto.getScentAnswer())) {
            throw new SurveyNotFoundException();
        }
        return surveyRepository.findByScentAnswer(surveyResponseDto.getScentAnswer());
    }

    public List<Survey> filterMoodAnswer(SurveyResponseDto surveyResponseDto) {
        if (!surveyRepository.existsByMoodAnswer(surveyResponseDto.getMoodAnswer())) {
            throw new SurveyNotFoundException();
        }
        return surveyRepository.findByMoodAnswerContaining(surveyResponseDto.getMoodAnswer());
    }

    private List<Survey> compareFilteredData(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData(surveyUtil.addAnswerListByType(filterGenderAnswer(surveyResponseDto), surveyRepository.findByGenderAnswer("젠더리스"))
                , filterScentAnswer(surveyResponseDto));
    }

    private List<Survey> retrySecondFiltering(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData
                (compareFilteredData(surveyResponseDto), surveyRepository.findByMoodAnswerNotContaining(surveyResponseDto.getMoodAnswer()));
    }

    public List<Perfume> compareData(SurveyResponseDto surveyResponseDto) {
        List<Survey> firstComparedList = compareGenderAndScentAnswer(surveyResponseDto);
        List<Survey> secondComparedList = surveyUtil.compareTwoFilteredSurveyData(firstComparedList, filterMoodAnswer(surveyResponseDto));
        List<Survey> thirdComparedList = surveyUtil.compareTwoFilteredSurveyData(
                isEmptyMoodColumn(surveyResponseDto, secondComparedList),
                surveyUtil.addAnswerListByType(
                        surveyRepository.findBySeasonAnswerContaining(surveyResponseDto.getSeasonAnswer()),
                        surveyRepository.findBySeasonAnswer("무관")));
        List<Survey> finalDataList = surveyUtil.compareTwoFilteredSurveyData(thirdComparedList, surveyUtil.addAnswerListByType(surveyRepository.findByStyleAnswer(
                surveyResponseDto.getStyleAnswer()), surveyRepository.findByStyleAnswer("디폴트")));

        return findPerfumeData(finalDataList, thirdComparedList);
    }

    public List<Survey> compareGenderAndScentAnswer(SurveyResponseDto surveyResponseDto) {
        return surveyUtil.compareTwoFilteredSurveyData(
                surveyUtil.addAnswerListByType(
                        filterGenderAnswer(surveyResponseDto),
                        surveyRepository.findByGenderAnswer("젠더리스")),
                filterScentAnswer(surveyResponseDto));
    }
    public List<Perfume> findPerfumeData(List<Survey> finalDataList, List<Survey> thirdComparedList) {
        List<Perfume> perfumeList = new ArrayList<>();
        for (int i = 0; i < surveyUtil.isEmptyFinalResult(finalDataList, thirdComparedList).size(); i++) {
            Long perfumeId = surveyUtil.isEmptyFinalResult(finalDataList, thirdComparedList).get(i).getId();
            perfumeList.add(perfumeRepository.findById(perfumeId).get());
        }
        return perfumeList;
    }


}