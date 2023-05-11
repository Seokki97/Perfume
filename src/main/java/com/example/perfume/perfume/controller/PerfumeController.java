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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("향수 이름: {} 의 향수 조회", perfumeRequestDto.getPerfumeName());
        return ResponseEntity.ok(perfumeService.findPerfumeByName(perfumeRequestDto));
    }

    @PostMapping("/find-by-brand")
    public ResponseEntity<List<Perfume>> findByBrandName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        log.info("향수 브랜드 : {} 의 향수 조회",perfumeRequestDto.getBrandName());
        return ResponseEntity.ok(perfumeService.findPerfumeByBrand(perfumeRequestDto));
    }

    @GetMapping("/show-all-data")
    public ResponseEntity<List<Perfume>> showAllData() {
        log.info("모든 향수 리스트 조회");
        return ResponseEntity.ok(perfumeService.showAllPerfumeData());
    }

    @GetMapping("/show-perfume/{id}")
    public ResponseEntity<FeatureResponseDto> showPerfumeDetails(@PathVariable Long id) {
        log.info("향수 Id : {} 의 세부정보 조회하기", id);
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

}

