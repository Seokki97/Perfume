package com.example.perfume.perfume.controller;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.perfume.domain.Perfume;

import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.perfume.service.RecommendService;
import com.example.perfume.survey.domain.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/perfume")
public class PerfumeDataController {

    private final PerfumeService perfumeService;
    private final RecommendService recommendService;

    @GetMapping("/save")
    public void saveData(Long id, PerfumeList perfumeList) throws IOException {
        perfumeService.savePerfumeData(id, perfumeList);
    }

    @PostMapping("/find_by_name")
    public ResponseEntity<Perfume> findByPerfumeName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        return ResponseEntity.ok(perfumeService.findPerfumeByName(perfumeRequestDto));
    }

    @PostMapping("/find_by_brand")
    public ResponseEntity<Perfume> findByBrandName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        return ResponseEntity.ok(perfumeService.findPerfumeByBrand(perfumeRequestDto));
    }

    @DeleteMapping("/delete")
    public void deleteAllData() {
        perfumeService.deleteAllData();
    }

    @GetMapping("/show_all_data")
    public ResponseEntity<List<Perfume>> showAllData() {
        return ResponseEntity.ok(perfumeService.showAllPerfumeData());
    }

    @PostMapping("/select_perfume")
    public ResponseEntity<List<Survey>> selectPerfume(@RequestBody PerfumeResponseDto perfumeResponseDto) {
        return ResponseEntity.ok(recommendService.showSimilarPerfume(perfumeResponseDto));
    }


}
