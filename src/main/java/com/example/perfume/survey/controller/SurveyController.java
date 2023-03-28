package com.example.perfume.survey.controller;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.repository.SurveyRepository;
import com.example.perfume.survey.service.DataService;
import com.example.perfume.survey.service.SimilarPerfumeService;
import com.example.perfume.survey.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final SimilarPerfumeService similarPerfumeService;

    private final Logger logger = LoggerFactory.getLogger("첫번째 기능 로그");

    public SurveyController(SurveyService surveyService, DataService dataService,
                            SurveyRepository surveyRepository, SimilarPerfumeService similarPerfumeService) {
        this.surveyService = surveyService;
        this.dataService = dataService;
        this.surveyRepository = surveyRepository;
        this.similarPerfumeService = similarPerfumeService;
    }

    @GetMapping("/save")
    public void saveSurveyData(Long id, SurveyList surveyList) throws IOException {
        dataService.saveSurveyData(id, surveyList);
    }

    @PostMapping("/show-perfume-by-survey")
    public ResponseEntity<List<Perfume>> showPerfumeDataBySurvey(@RequestBody SurveyRequestDto surveyRequestDto) {
        logger.info("api 호출");
        return ResponseEntity.ok(surveyService.showPerfumeListBySurvey(surveyRequestDto));
    }

    @PostMapping("/show")
    public ResponseEntity<List<Survey>> showSelectedData(@RequestBody SurveyRequestDto surveyRequestDto) {
        return ResponseEntity.ok(surveyRepository.findByScentAnswer(surveyRequestDto.getScentAnswer()));
    }

    @GetMapping("/show-similar-perfume/{id}")
    public ResponseEntity<List<Perfume>> showSimilarData(@PathVariable("id") Long id) {
        return ResponseEntity.ok(similarPerfumeService.showSimilarPerfume(id));
    }

}
