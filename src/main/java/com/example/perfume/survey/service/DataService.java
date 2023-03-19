package com.example.perfume.survey.service;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.crawling.service.SurveyCSVFileLoading;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataService {
    private final SurveyCSVFileLoading surveyCSVFileLoading;
    private final PerfumeService perfumeService;
    private final SurveyService surveyService;

    public DataService(SurveyService surveyService, SurveyCSVFileLoading surveyCSVFileLoading, PerfumeService perfumeService) {
        this.surveyService = surveyService;
        this.surveyCSVFileLoading = surveyCSVFileLoading;
        this.perfumeService = perfumeService;
    }

    private SurveyResponseDto makeList(Long id, int firstIndex, SurveyList surveyList) {
        return SurveyResponseDto.builder()
                .id(id)
                .genderAnswer(surveyList.getFirstAnswer().get(firstIndex))
                .scentAnswer(surveyList.getSecondAnswer().get(firstIndex))
                .moodAnswer(surveyList.getThirdAnswer().get(firstIndex))
                .seasonAnswer(surveyList.getFourthAnswer().get(firstIndex))
                .styleAnswer(surveyList.getFifthAnswer().get(firstIndex))
                .build();
    }

    public void saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        surveyList = surveyCSVFileLoading.extractAllSurveyData(surveyList);
        for (int firstIndex = 0; firstIndex < surveyList.getMaxSize(); firstIndex++) {
            Long secondIndex = (long) firstIndex + 1;
            Survey surveyDataSet = makeList(id, firstIndex, surveyList).toEntity(perfumeService.findPerfumeById(secondIndex));
            surveyService.saveSurveyData(surveyDataSet);
        }
    }
}
