package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SurveyConverter {

    public List<Perfume> convertToPerfumeData(List<Survey> surveyList) {
        return surveyList.stream()
                .map(Survey::getPerfume).collect(Collectors.toList());
    }
}
