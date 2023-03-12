package com.example.perfume.survey.controller;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.dto.surveyDto.SurveyResponseDto;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.DataService;
import com.example.perfume.survey.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<List<Perfume>> showPerfumeDataBySurvey(@RequestBody SurveyResponseDto surveyResponseDto) {
        return ResponseEntity.ok(surveyService.compareData(surveyResponseDto));
    }

    @PostMapping("/show")
    public ResponseEntity<List<Survey>> showSelectedData(@RequestBody SurveyRequestDto surveyRequestDto) {
        return ResponseEntity.ok(surveyRepository.findByScentAnswer(surveyRequestDto.getScentAnswer()));
    }

}
