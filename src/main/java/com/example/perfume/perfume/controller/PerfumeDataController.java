package com.example.perfume.perfume.controller;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.perfume.domain.Perfume;

import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import com.example.perfume.survey.service.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/perfume")
public class PerfumeDataController {

    private final PerfumeService perfumeService;

    private final FeatureService featureService;

    public PerfumeDataController(PerfumeService perfumeService, FeatureService featureService) {
        this.perfumeService = perfumeService;
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

    @GetMapping("/show-perfume/{id}")
    public ResponseEntity<FeatureResponseDto> showPerfumeDetails(@PathVariable Long id) {
        return ResponseEntity.ok(featureService.showFeatureDetails(id));
    }

    @GetMapping("/show-all-brand")
    public ResponseEntity<List<String>> showAllBrandName(){
        return ResponseEntity.ok(perfumeService.showBrandList());
    }

    @GetMapping("/perfume-image")
    public ResponseEntity<PerfumeResponseDto> showPerfumeImage(@RequestBody PerfumeRequestDto perfumeRequestDto){
        return ResponseEntity.ok(perfumeService.showPerfumeImage(perfumeRequestDto));
    }
}
