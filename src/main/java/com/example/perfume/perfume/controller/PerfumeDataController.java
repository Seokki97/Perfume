package com.example.perfume.perfume.controller;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.perfume.domain.Perfume;

import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.service.PerfumeData;
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

    private final PerfumeData perfumeData;


    public PerfumeDataController(PerfumeService perfumeService, PerfumeData perfumeData) {
        this.perfumeService = perfumeService;
        this.perfumeData = perfumeData;
    }

    @GetMapping("/save")
    public ResponseEntity<Void> saveData(Long id, PerfumeList perfumeList) throws IOException {
        perfumeData.savePerfumeData(id, perfumeList);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAllData() {
        perfumeData.deleteAllData();
        return ResponseEntity.noContent().build();
    }

}
