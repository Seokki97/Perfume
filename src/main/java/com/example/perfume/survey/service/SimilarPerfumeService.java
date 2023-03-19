package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.domain.SurveyType;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimilarPerfumeService {

    private final SurveyService surveyService;
    private final SurveyRepository surveyRepository;
    private final SurveyUtil surveyUtil;

    public SimilarPerfumeService(SurveyService surveyService,
                                 SurveyRepository surveyRepository,SurveyUtil surveyUtil) {
        this.surveyService = surveyService;
        this.surveyRepository = surveyRepository;
        this.surveyUtil = surveyUtil;
    }

    private List<Survey> findExceptRequestedPerfume(List<Survey> surveyList, SurveyRequestDto surveyRequestDto) {
        return surveyList.stream()
                .filter(x -> x.getId() != surveyRequestDto.getId())
                .collect(Collectors.toList());
    }

    public List<Perfume> showSimilarPerfume(PerfumeRequestDto perfumeRequestDto) {
        Survey survey = surveyService.findSurveyById(perfumeRequestDto.getId());
        List<Survey> surveyList = surveyRepository.findByGenderAnswerAndScentAnswer(survey.getGenderAnswer(), survey.getScentAnswer());
        List<Survey> filteredSurveys = surveyService.filterByMood(splitMoodAnswer(survey), surveyList);
        filteredSurveys = findExceptRequestedPerfume(filteredSurveys, SurveyRequestDto.makeDto(survey));

        return surveyService.convertToPerfumeData(filteredSurveys);
    }


    public SurveyRequestDto splitMoodAnswer(Survey survey) {
        String[] moodAnswerArray = survey.getMoodAnswer().split("\\s");
        if (moodAnswerArray.length == 1) {
            return SurveyRequestDto.builder().moodAnswer(survey.getMoodAnswer()).build();
        }
        return SurveyRequestDto.builder().moodAnswer(moodAnswerArray[0]).build();
    }

    //만약 비어있을경우에 mood를 split해서 사용
    public String isEmptyMood(List<Survey> perfumeList, Survey survey) {
        if (perfumeList.isEmpty()) {
            return survey.getMoodAnswer().split(" ")[0];
        }
        return survey.getMoodAnswer();
    }
}
