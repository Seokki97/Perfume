package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Feature;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyUtil {

    public List<String> splitAnswerOfSurvey(String answerOfSurvey) {


        List<String> answerOfSurveyList = Arrays.stream(answerOfSurvey.split(" ")).collect(Collectors.toList());

        return answerOfSurveyList;
    }
}
