package com.example.perfume.perfume.controller;

import com.example.perfume.crawling.domain.perfume.PerfumeList;

import com.example.perfume.perfume.service.PerfumeData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/perfume")
public class PerfumeDataController {

    private final PerfumeData perfumeData;

    public PerfumeDataController(PerfumeData perfumeData) {
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
