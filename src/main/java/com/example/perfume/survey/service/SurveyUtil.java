package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SurveyUtil {

    private static final String BLANK = "\\s";

    private static final int MOOD_COLUMN_SIZE = 1;

    private int getRandomMoodAnswer(String[] moodAnswerArray) {
        Random random = new Random();
        return random.nextInt(moodAnswerArray.length);
    }

    public String showMoodAnswer(Survey survey) {
        String[] moodAnswerArray = survey.getMoodAnswer().split(BLANK);
        int randomMoodAnswer = getRandomMoodAnswer(moodAnswerArray);

        if (isMoodAnswerUnique(moodAnswerArray.length)) {
            return survey.getMoodAnswer();
        }
        return moodAnswerArray[randomMoodAnswer];
    }

    private boolean isMoodAnswerUnique(int arrayLength) {
        return arrayLength == MOOD_COLUMN_SIZE;
    }

    public List<Perfume> convertToPerfumeData(List<Survey> surveyList) {
        return surveyList.stream()
                .map(Survey::getPerfume).collect(Collectors.toList());
    }

    public boolean isEmptyRecommendedPerfumeList(List<Survey> surveyList) {
        return surveyList.isEmpty();
    }
}
