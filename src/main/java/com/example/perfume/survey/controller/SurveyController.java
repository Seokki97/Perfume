package com.example.perfume.survey.controller;

import com.example.perfume.survey.domain.Feature;
import com.example.perfume.survey.dto.featureDto.FeatureDto;
import com.example.perfume.survey.dto.featureDto.FeatureRequestDto;
import com.example.perfume.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;


    @PostMapping("/first_question")
    public ResponseEntity<List<Feature>> getFirstQuestionData( @RequestBody FeatureDto featureDto) {

        return ResponseEntity.ok(surveyService.findDataFromAnswerTest(featureDto));
    }

    @PostMapping("/save")
    public void saveData(Long id,@RequestBody FeatureRequestDto featureRequestDto) {
        surveyService.saveAllData(id,featureRequestDto);
    }
}
