package com.example.perfume.survey.service;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.crawling.service.SurveyCSVFileLoading;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataService {

    private final SurveyCSVFileLoading surveyCSVFileLoading;
    private final PerfumeService perfumeService;
    private final SurveyService surveyService;

    public DataService(SurveyService surveyService, SurveyCSVFileLoading surveyCSVFileLoading,
                       PerfumeService perfumeService) {
        this.surveyService = surveyService;
        this.surveyCSVFileLoading = surveyCSVFileLoading;
        this.perfumeService = perfumeService;
    }

    public void saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        surveyList = surveyCSVFileLoading.extractAllSurveyData(surveyList);
        int surveyListSize = surveyList.getMaxSize();
        for (int firstIndex = 0; firstIndex < surveyListSize; firstIndex++) {
            Long secondIndex = (long) firstIndex + 1;
            Survey surveyDataSet = SurveyResponseDto.makeList(id, firstIndex, surveyList)
                    .toEntity(perfumeService.findPerfumeById(secondIndex));
            surveyService.saveSurveyData(surveyDataSet);
        }
    }
}
