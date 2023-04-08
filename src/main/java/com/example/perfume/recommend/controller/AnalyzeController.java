package com.example.perfume.recommend.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.recommend.service.analyze.AnalyzeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class AnalyzeController {

    private final AnalyzeService analyzeService;

    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @LoginCheck
    @GetMapping("/show-result/{id}")
    public ResponseEntity<Map<String, Long>> showMostRecommendedPerfume(@PathVariable("id") Long memberId) {

        return ResponseEntity.ok(analyzeService.showAnalyzedData(memberId));
    }
}
