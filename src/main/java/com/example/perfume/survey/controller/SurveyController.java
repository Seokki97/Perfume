package com.example.perfume.survey.controller;

import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.SurveyRequestDto;
import com.example.perfume.survey.dto.featureDto.SurveyResponseDto;
import com.example.perfume.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;


    @PostMapping("/first_question")
    public ResponseEntity<List<Survey>> getFirstQuestionData(@RequestBody SurveyResponseDto surveyResponseDto) {

        return ResponseEntity.ok(surveyService.findDataFromAnswerTest(surveyResponseDto));
    }

    @PostMapping("/save")
    public void saveData(Long id,@RequestBody SurveyRequestDto surveyRequestDto) {
    }
}
