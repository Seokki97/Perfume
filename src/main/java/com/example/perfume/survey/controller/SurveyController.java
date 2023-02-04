package com.example.perfume.survey.controller;

import com.example.perfume.survey.domain.Feature;
import com.example.perfume.survey.dto.featureDto.FeatureRequestDto;
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
    public ResponseEntity<List<Feature>> getFirstQuestionData(@RequestBody FeatureRequestDto answerOfSurvey) {

        surveyService.findDataFromAnswerData(answerOfSurvey);
        return ResponseEntity.ok(surveyService.findDataFromAnswerData(answerOfSurvey));
    }

    @GetMapping("/save")
    public void saveData(Long id) {
        surveyService.saveAllData(id);
    }
}
