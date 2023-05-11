package com.example.perfume.survey.controller;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.controller.docs.SurveyControllerDocs;
import com.example.perfume.survey.dto.surveyDto.SurveyRequestDto;
import com.example.perfume.survey.service.DataService;
import com.example.perfume.survey.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/survey")
public class SurveyController implements SurveyControllerDocs {

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
    public ResponseEntity<List<Perfume>> showPerfumeDataBySurvey(@RequestBody final SurveyRequestDto surveyRequestDto) {
        log.info("설문 기반의 향수 찾기 Gender : {}, Scent : {}, Mood : {}, Season : {}, Style : {}",
                surveyRequestDto.getGenderAnswer(),
                surveyRequestDto.getScentAnswer(),
                surveyRequestDto.getMoodAnswer(),
                surveyRequestDto.getSeasonAnswer(),
                surveyRequestDto.getStyleAnswer());
        return ResponseEntity.ok(surveyService.showPerfumeListBySurvey(surveyRequestDto));
    }

}
