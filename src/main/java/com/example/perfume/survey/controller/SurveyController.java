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
    private final SurveyRepository surveyRepository;
    private final SimilarPerfumeService similarPerfumeService;

    public SurveyController(SurveyService surveyService, DataService dataService,
                            SurveyRepository surveyRepository, SimilarPerfumeService similarPerfumeService) {
        this.surveyService = surveyService;
        this.dataService = dataService;
        this.surveyRepository = surveyRepository;
        this.similarPerfumeService = similarPerfumeService;
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

    @PostMapping("/show")
    public ResponseEntity<List<Survey>> showSelectedData(@RequestBody SurveyRequestDto surveyRequestDto) {
        return ResponseEntity.ok(surveyRepository.findByScentAnswer(surveyRequestDto.getScentAnswer()));
    }

    @GetMapping("/show-similar-perfume/{id}")
    public ResponseEntity<List<Perfume>> showSimilarData(@PathVariable("id") Long id) {
        LoggerUtil.countSecondApi("비슷한 향수 추천 (두번째 기능) API 호출 횟수: ");
        return ResponseEntity.ok(similarPerfumeService.showSimilarPerfume(id));
    }

}
