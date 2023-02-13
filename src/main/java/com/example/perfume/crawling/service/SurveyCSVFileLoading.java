package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.domain.survey.SurveyList;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyCSVFileLoading extends CSVFileLoading {

    private List<String> surveyListTest;

    private static final int COLUMN_LENGTH = 5;

    private static final int GENDER_COLUMN = 0; //남녀
    private static final int SCENT_COLUMN = 1; //향
    private static final int MOOD_COLUMN = 2; //무드
    private static final int SEASON_COLUMN = 3; //계절
    private static final int STYLE_COLUMN = 4; //스타일

    public SurveyCSVFileLoading(List<String> surveyListTest) throws FileNotFoundException, UnsupportedEncodingException {

        this.surveyListTest = surveyListTest;
    }

    public List<String> makeSurveyList(String[] array) {
        for (int i = 0; i < array.length; i++) {
            surveyListTest.add(array[i]);
        }
        return surveyListTest;
    }

    public List<String> splitSurveyData() throws IOException {
        String surveyData;
        while ((surveyData = bufferedReader.readLine()) != null) {
            makeSurveyList(splitData(surveyData));
        }
        return surveyListTest;
    }

    public List<String> extractAnswerFromSurvey(int columnName) throws IOException {
        splitSurveyData();
        List<String> testList = new ArrayList<>();
        for (int i = columnName; i < surveyListTest.size(); i += COLUMN_LENGTH) {
            testList.add(surveyListTest.get(i));
        }

        return testList;
    }

    public SurveyList extractAllSurveyData(SurveyList surveyList) throws IOException {
        surveyList = surveyList.builder().firstAnswer(extractAnswerFromSurvey(GENDER_COLUMN))
                .secondAnswer(extractAnswerFromSurvey(SCENT_COLUMN))
                .thirdAnswer(extractAnswerFromSurvey(MOOD_COLUMN))
                .fourthAnswer(extractAnswerFromSurvey(SEASON_COLUMN))
                .fifthAnswer(extractAnswerFromSurvey(STYLE_COLUMN))
                .build();
        return surveyList;
    }
}
