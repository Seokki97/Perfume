package com.example.perfume.survey.controller;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyRequestDto;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.service.DataService;
import com.example.perfume.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;


    private final DataService dataService;

    @GetMapping("/save")
    public void saveSurveyData(Long id, SurveyList surveyList ) throws IOException {
        dataService.saveSurveyData(id,surveyList);
    }
    @PostMapping("/first_question")
    public ResponseEntity<List<Survey>> getFirstQuestionData(@RequestBody SurveyResponseDto surveyResponseDto) {

        return ResponseEntity.ok(surveyService.findDataFromAnswerTest(surveyResponseDto));
    }


    @DeleteMapping("/delete")
    public void deleteData(){
        dataService.deleteAllData();
    }
}
