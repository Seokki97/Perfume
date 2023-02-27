package com.example.perfume.survey.service;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.crawling.service.SurveyCSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataService {

    private final SurveyRepository surveyRepository;
    private final SurveyCSVFileLoading surveyCSVFileLoading;
    private final PerfumeRepository perfumeRepository;

    public DataService(SurveyRepository surveyRepository, SurveyCSVFileLoading surveyCSVFileLoading, PerfumeRepository perfumeRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyCSVFileLoading = surveyCSVFileLoading;
        this.perfumeRepository = perfumeRepository;

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
            Long secondIndex = (long) firstIndex + 1;
            Survey surveyDataSet = makeList(id, firstIndex, surveyList).toEntity(getPerfumeId(secondIndex));
            surveyRepository.save(surveyDataSet);
        }
    }

    public Perfume getPerfumeId(Long id) {
        return perfumeRepository.findById(id).get();
    }


}
