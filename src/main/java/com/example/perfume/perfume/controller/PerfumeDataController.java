package com.example.perfume.perfume.controller;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.perfume.domain.Perfume;

import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.perfume.service.SimilarPerfumeRecommend;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import com.example.perfume.survey.service.FeatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/perfume")
public class PerfumeDataController {

    private final PerfumeService perfumeService;
    private final SimilarPerfumeRecommend similarPerfumeRecommend;

    private final FeatureService featureService;

    public PerfumeDataController(PerfumeService perfumeService, SimilarPerfumeRecommend similarPerfumeRecommend, FeatureService featureService) {
        this.perfumeService = perfumeService;
        this.similarPerfumeRecommend = similarPerfumeRecommend;
        this.featureService = featureService;
    }

    @GetMapping("/save")
    public void saveData(Long id, PerfumeList perfumeList) throws IOException {
        perfumeService.savePerfumeData(id, perfumeList);
    }

    @PostMapping("/find-by-name")
    public ResponseEntity<List<Perfume>> findByPerfumeName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        return ResponseEntity.ok(perfumeService.findPerfumeByName(perfumeRequestDto));
    }

    @PostMapping("/find-by-brand")
    public ResponseEntity<List<Perfume>> findByBrandName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        return ResponseEntity.ok(perfumeService.findPerfumeByBrand(perfumeRequestDto));
    }

    @DeleteMapping("/delete")
    public void deleteAllData() {
        perfumeService.deleteAllData();
    }

    @GetMapping("/show-all-data")
    public ResponseEntity<List<Perfume>> showAllData() {
        return ResponseEntity.ok(perfumeService.showAllPerfumeData());
    }

    @PostMapping("/select-similar-perfume")
    public ResponseEntity<List<Perfume>> selectSimilarPerfume(@RequestBody PerfumeResponseDto perfumeResponseDto) {
        return ResponseEntity.ok(similarPerfumeRecommend.showSimilarPerfume(perfumeResponseDto));
    }

    @GetMapping("/show-perfume/{id}")
    public ResponseEntity<FeatureResponseDto> showPerfumeDetails(@PathVariable Long id) {
        return ResponseEntity.ok(featureService.showFeatureDetails(id));
    }


}
