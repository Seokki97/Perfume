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

    public SurveyResponseDto makeList(Long id, int firstIndex, SurveyList surveyList) {
        return new SurveyResponseDto(id,
                surveyList.getFirstAnswer().get(firstIndex),
                surveyList.getSecondAnswer().get(firstIndex),
                surveyList.getThirdAnswer().get(firstIndex),
                surveyList.getFourthAnswer().get(firstIndex),
                surveyList.getFifthAnswer().get(firstIndex));
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
