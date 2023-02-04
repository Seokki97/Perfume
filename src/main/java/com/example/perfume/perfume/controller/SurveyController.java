package com.example.perfume.perfume.controller;

import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

    SurveyService surveyService;

    @PostMapping("/first_question")
    public ResponseEntity<List<Feature>> getFirstQuestionData(@RequestParam(name = "answerOfSurvey") List<String> answerOfSurvey) {

        surveyService.findDataFromAnswerData(answerOfSurvey);
        return ResponseEntity.ok(surveyService.findDataFromAnswerData(answerOfSurvey));
    }

    @GetMapping("/save")
    public void saveData(){
        surveyService.saveAllData();
    }
}
