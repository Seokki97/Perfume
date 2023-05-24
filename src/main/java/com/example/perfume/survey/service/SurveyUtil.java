package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SurveyUtil {

    private static final String BLANK = "\\s";

    private static final int MOOD_COLUMN_SIZE = 1;

    public int getRandomMoodAnswer(String[] moodAnswerArray) {
        Random random = new Random();
        return random.nextInt(moodAnswerArray.length);
    }

    public SurveyRequestDto showMoodAnswer(Survey survey) {
        String[] moodAnswerArray = survey.getMoodAnswer().split(BLANK);
        int randomMoodAnswer = getRandomMoodAnswer(moodAnswerArray);

        if (isMoodAnswerUnique(moodAnswerArray.length)) {
            return SurveyRequestDto.builder().moodAnswer(survey.getMoodAnswer()).build();
        }
        return SurveyRequestDto.builder().moodAnswer(moodAnswerArray[randomMoodAnswer]).build();
    }

    private boolean isMoodAnswerUnique(int arrayLength) {
        return arrayLength == MOOD_COLUMN_SIZE;
    }
}
