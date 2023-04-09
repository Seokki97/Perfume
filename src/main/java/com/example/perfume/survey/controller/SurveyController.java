package com.example.perfume.survey.controller;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.log.LoggerUtil;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.DataService;
import com.example.perfume.survey.service.SimilarPerfumeService;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final DataService dataService;

    public SurveyController(SurveyService surveyService, DataService dataService) {
        this.surveyService = surveyService;
        this.dataService = dataService;

    }

    @GetMapping("/save")
    public ResponseEntity<Void> saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        dataService.saveSurveyData(id, surveyList);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/show-perfume-by-survey")
    public ResponseEntity<List<Perfume>> showPerfumeDataBySurvey(@RequestBody SurveyRequestDto surveyRequestDto) {
        LoggerUtil.countFirstApi("나에게 잘 어울리는 향수 (첫번째 기능) API 호출 횟수 : ");
        return ResponseEntity.ok(surveyService.showPerfumeListBySurvey(surveyRequestDto));
    }

}
