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

    private static final int FIRST_COLUMN = 0; //남녀
    private static final int SECOND_COLUMN = 1; //향
    private static final int THIRD_COLUMN = 2; //무드
    private static final int FOURTH_COLUMN = 3; //계절
    private static final int FIFTH_COLUMN = 4; //스타일

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
        surveyList = surveyList.builder().firstAnswer(extractAnswerFromSurvey(FIRST_COLUMN))
                .secondAnswer(extractAnswerFromSurvey(SECOND_COLUMN))
                .thirdAnswer(extractAnswerFromSurvey(THIRD_COLUMN))
                .fourthAnswer(extractAnswerFromSurvey(FOURTH_COLUMN))
                .fifthAnswer(extractAnswerFromSurvey(FIFTH_COLUMN))
                .build();
        return surveyList;
    }
}
