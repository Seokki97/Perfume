package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyUtil {

    private static final String BLANK = "\\s";
    private static final int FIRST_MOOD = 0;
    private static final int MOOD_COLUMN_SIZE = 1;

    public List<Survey> compareTwoFilteredSurveyData(List<Survey> firstDataList, List<Survey> secondDataList) {
        return firstDataList.stream().filter(o -> secondDataList.stream()
                .anyMatch(n -> o.getId().equals(n.getId()))).collect(Collectors.toList());
    }

    public List<Survey> addList(List<Survey> firstDataList, List<Survey> secondDataList) {
        List<Survey> addedList = new ArrayList<>();
        addedList.addAll(firstDataList);
        addedList.addAll(secondDataList);
        return addedList;
    }

    public List<Survey> isEmptyFinalResult(List<Survey> finalResult, List<Survey> beforeResult) {
        if (finalResult.isEmpty()) {
            return beforeResult;
        }
        return finalResult;
    }
    public SurveyRequestDto splitMoodAnswer(Survey survey) {
        String[] moodAnswerArray = survey.getMoodAnswer().split(BLANK);
        if (moodAnswerArray.length == MOOD_COLUMN_SIZE) {
            return SurveyRequestDto.builder().moodAnswer(survey.getMoodAnswer()).build();
        }
        return SurveyRequestDto.builder().moodAnswer(moodAnswerArray[FIRST_MOOD]).build();
    }
}
