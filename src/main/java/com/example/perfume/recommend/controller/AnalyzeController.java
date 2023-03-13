package com.example.perfume.recommend.controller;

import com.example.perfume.recommend.dto.PerfumeAnalyzeResponse;
import com.example.perfume.recommend.service.PerfumeAnalyze;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class AnalyzeController {

    private final PerfumeAnalyze perfumeAnalyze;

    public AnalyzeController(PerfumeAnalyze perfumeAnalyze){
        this.perfumeAnalyze = perfumeAnalyze;
    }
    @GetMapping("/show-result/{id}")
    public ResponseEntity<PerfumeAnalyzeResponse> showMostRecommendedPerfume(@PathVariable("id") Long memberId){
        return ResponseEntity.ok(perfumeAnalyze.filterMostRecommendedPerfumeName(memberId));

    }
}
