package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.crawling.domain.survey.SurveyType;
import com.example.perfume.survey.domain.ScentType;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class SurveyCSVFileLoading extends CSVFileLoading {

    private List<String> surveyListTest;

    private static final int COLUMN_LENGTH = 5;
    public final String FILE_PATH = "C:/Users/wnstj/gradu/SurveyData.csv";

    public BufferedReader bufferedReader;

    public SurveyCSVFileLoading(List<String> surveyListTest) throws FileNotFoundException, UnsupportedEncodingException {
        this.bufferedReader = new BufferedReader(importFile(FILE_PATH));
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
        surveyList = surveyList.builder().firstAnswer(extractAnswerFromSurvey(SurveyType.GENDER.selectTypeValue()))
                .secondAnswer(extractAnswerFromSurvey(SurveyType.SCENT.selectTypeValue()))
                .thirdAnswer(extractAnswerFromSurvey(SurveyType.MOOD.selectTypeValue()))
                .fourthAnswer(extractAnswerFromSurvey(SurveyType.SEASON.selectTypeValue()))
                .fifthAnswer(extractAnswerFromSurvey(SurveyType.STYLE.selectTypeValue()))
                .build();
        return surveyList;
    }
}
