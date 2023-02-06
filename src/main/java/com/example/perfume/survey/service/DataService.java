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

    public SurveyResponseDto makeList(Long id, int firstIndex, SurveyList surveyList) {
        SurveyResponseDto surveyResponseDto = new SurveyResponseDto(id,
                surveyList.getFirstAnswer().get(firstIndex),
                surveyList.getSecondAnswer().get(firstIndex),
                surveyList.getThirdAnswer().get(firstIndex),
                surveyList.getFourthAnswer().get(firstIndex),
                surveyList.getFifthAnswer().get(firstIndex));
        return surveyResponseDto;
    }

    public void saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        surveyList = surveyCSVFileLoading.extractAllSurveyData(surveyList);

        for (int firstIndex = 0; firstIndex < surveyList.getMaxSize(); firstIndex++) {

            Survey surveyDataSet = makeList(id, firstIndex, surveyList).toEntity();
            surveyRepository.save(surveyDataSet);
        }
    }

}
