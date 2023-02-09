package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SurveyUtil {

    public List<Survey> compareTwoFilteredSurveyData(List<Survey> firstDataList, List<Survey> secondDataList) {
        return firstDataList.stream()
                .filter(o -> secondDataList.stream().anyMatch(Predicate.isEqual(o)))
                .collect(Collectors.toList());
    }

    //다른데서 용이하게 쓰이니까 util로 빼는것도 낫배드 할 것 같음!
    public List<Survey> addList(List<Survey> firstDataList, List<Survey> secondDataList) {
        List<Survey> addedList = new ArrayList<>();
        addedList.addAll(firstDataList);
        addedList.addAll(secondDataList);
        return addedList;
    }
}
