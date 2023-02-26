package com.example.perfume.survey.controller;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.DataService;
import com.example.perfume.survey.service.FeatureService;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    private final DataService dataService;
    private final SurveyRepository surveyRepository;

    public SurveyController(SurveyService surveyService, DataService dataService,
                            SurveyRepository surveyRepository) {
        this.surveyService = surveyService;
        this.dataService = dataService;
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/save")
    public void saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        dataService.saveSurveyData(id, surveyList);
    }

    @PostMapping("/show-perfume-by-survey")
    public ResponseEntity<List<Survey>> showPerfumeDataBySurvey(@RequestBody SurveyResponseDto surveyResponseDto) {
        return ResponseEntity.ok(surveyService.compareData(surveyResponseDto));
    }

    @GetMapping("/show-feature")
    public ResponseEntity<FeatureService> showFeatureSummary(@RequestBody SurveyResponseDto surveyResponseDto) {

        return null;
    }

    @DeleteMapping("/delete")
    public void deleteData() {
        dataService.deleteAllData();
    }

    @PostMapping("/show")
    public ResponseEntity<List<Survey>> showSelectedData(@RequestBody SurveyRequestDto surveyRequestDto) {
        return ResponseEntity.ok(surveyRepository.findByScentAnswer(surveyRequestDto.getScentAnswer()));
    }

}
