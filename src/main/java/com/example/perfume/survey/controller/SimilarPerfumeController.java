package com.example.perfume.survey.controller;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.controller.docs.SimilarPerfumeControllerDocs;
import com.example.perfume.survey.service.SimilarPerfumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/survey")
public class SimilarPerfumeController implements SimilarPerfumeControllerDocs {
    private final SimilarPerfumeService similarPerfumeService;

    public SimilarPerfumeController(SimilarPerfumeService similarPerfumeService) {
        this.similarPerfumeService = similarPerfumeService;
    }

    @GetMapping("/show-similar-perfume/{id}")
    public ResponseEntity<List<Perfume>> showSimilarData(@PathVariable("id") Long id) {
        log.info("유사한 향수 조회 Perfume Id : {}", id);
        return ResponseEntity.ok(similarPerfumeService.showSimilarPerfume(id));
    }
}
