package com.example.perfume.survey.controller;

import com.example.perfume.log.LoggerUtil;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.controller.docs.SimilarPerfumeControllerDocs;
import com.example.perfume.survey.service.SimilarPerfumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SimilarPerfumeController implements SimilarPerfumeControllerDocs {

    private final SimilarPerfumeService similarPerfumeService;

    public SimilarPerfumeController(SimilarPerfumeService similarPerfumeService) {
        this.similarPerfumeService = similarPerfumeService;
    }

    @GetMapping("/show-similar-perfume/{id}")
    public ResponseEntity<List<Perfume>> showSimilarData(@PathVariable("id") Long id) {
        LoggerUtil.countSecondApi("비슷한 향수 추천 (두번째 기능) API 호출 횟수: ");
        return ResponseEntity.ok(similarPerfumeService.showSimilarPerfume(id));
    }
}
