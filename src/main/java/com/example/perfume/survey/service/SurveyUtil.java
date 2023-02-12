package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SurveyUtil {

    private final SurveyRepository surveyRepository;

    public SurveyUtil(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public List<Survey> compareTwoFilteredSurveyData(List<Survey> firstDataList, List<Survey> secondDataList) {
        return firstDataList.stream()
                .filter(o -> secondDataList.stream().anyMatch(Predicate.isEqual(o)))
                .collect(Collectors.toList());
    }

    public List<Survey> addList(List<Survey> firstDataList, List<Survey> secondDataList) {
        List<Survey> addedList = new ArrayList<>();
        addedList.addAll(firstDataList);
        addedList.addAll(secondDataList);
        return addedList;
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
        ;
        return surveyRepository.findByScentAnswer(surveyResponseDto.getScentAnswer());
    }

    public List<Survey> filterMoodAnswer(SurveyResponseDto surveyResponseDto) {
        if(!surveyRepository.existsByMoodAnswer(surveyResponseDto.getMoodAnswer())){
            throw new SurveyNotFoundException();
        }
        return surveyRepository.findByMoodAnswerContaining(surveyResponseDto.getMoodAnswer());
    }
}
