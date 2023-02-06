package com.example.perfume.survey.service;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.crawling.service.SurveyCSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataService {

    private final SurveyRepository surveyRepository;
    private final SurveyCSVFileLoading surveyCSVFileLoading;

    public DataService(SurveyRepository surveyRepository, SurveyCSVFileLoading surveyCSVFileLoading) {
        this.surveyRepository = surveyRepository;
        this.surveyCSVFileLoading = surveyCSVFileLoading;

    }

    public void saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        surveyList = surveyCSVFileLoading.extractAllSurveyData(surveyList);

        for (int i = 0; i < surveyList.getMaxSize(); i++) {

            SurveyResponseDto surveyResponseDto = new SurveyResponseDto(id,
                    surveyList.getFirstAnswer().get(i),
                    surveyList.getSecondAnswer().get(i),
                    surveyList.getThirdAnswer().get(i),
                    surveyList.getFourthAnswer().get(i),
                    surveyList.getFifthAnswer().get(i));

            Survey surveyDataSet = surveyResponseDto.toEntity();
            surveyRepository.save(surveyDataSet);
        }
    }
}
