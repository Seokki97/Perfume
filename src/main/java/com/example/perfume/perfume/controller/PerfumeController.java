package com.example.perfume.perfume.controller;


import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.perfume.controller.docs.PerfumeControllerDocs;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.dto.story.StoryResponse;
import com.example.perfume.perfume.service.PerfumeService;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import com.example.perfume.survey.service.FeatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfume")
public class PerfumeController implements PerfumeControllerDocs {

    private final PerfumeService perfumeService;

    private final FeatureService featureService;

    public PerfumeController(PerfumeService perfumeService, FeatureService featureService) {
        this.perfumeService = perfumeService;
        this.featureService = featureService;
    }

    @PostMapping("/find-by-name")
    public ResponseEntity<List<Perfume>> findByPerfumeName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        return ResponseEntity.ok(perfumeService.findPerfumeByName(perfumeRequestDto));
    }

    @PostMapping("/find-by-brand")
    public ResponseEntity<List<Perfume>> findByBrandName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        return ResponseEntity.ok(perfumeService.findPerfumeByBrand(perfumeRequestDto));
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
    public ResponseEntity<List<String>> showAllBrandName() {
        return ResponseEntity.ok(perfumeService.showBrandList());
    }

    @LoginCheck
    @GetMapping("/perfume-image")
    public ResponseEntity<PerfumeResponseDto> showPerfumeImage(@RequestParam("perfumeName") String perfumeName) {
        return ResponseEntity.ok(perfumeService.showPerfumeImage(perfumeName));
    }

    @GetMapping("/show-story/{id}")
    public ResponseEntity<StoryResponse> showPerfumeStory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(perfumeService.showPerfumeStory(id));
    }
}

