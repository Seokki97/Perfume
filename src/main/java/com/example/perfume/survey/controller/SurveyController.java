package com.example.perfume.survey.controller;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.service.DataService;
import com.example.perfume.survey.service.FeatureService;
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

    public SurveyController(SurveyService surveyService, DataService dataService){
        this.surveyService = surveyService;
        this.dataService = dataService;
    }

    @GetMapping("/save")
    public void saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        dataService.saveSurveyData(id, surveyList);
    }

    @PostMapping("/answer")
    public ResponseEntity<List<Survey>> getFirstQuestionData(@RequestBody SurveyResponseDto surveyResponseDto) {

        return ResponseEntity.ok(surveyService.compareData(surveyResponseDto));
    }

    @GetMapping("/show-feature")
    public ResponseEntity<FeatureService> showFeatureSummary(@RequestBody SurveyResponseDto surveyResponseDto){

        return null;
    }

    @DeleteMapping("/delete")
    public void deleteData() {
        dataService.deleteAllData();
    }
}
