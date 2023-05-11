package com.example.perfume.recommend.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.recommend.controller.docs.AnalyzeControllerDocs;
import com.example.perfume.recommend.service.analyze.AnalyzeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/member")
public class AnalyzeController implements AnalyzeControllerDocs {

    private final AnalyzeService analyzeService;

    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @LoginCheck
    @GetMapping("/show-result/{id}")
    public ResponseEntity<Map<String, Long>> showMostRecommendedPerfume(@PathVariable("id") Long memberId) {
        log.info("memberId : {} 의 추천받은 향수 결과 분석 요청", memberId);
        return ResponseEntity.ok(analyzeService.showAnalyzedData(memberId));
    }
}
