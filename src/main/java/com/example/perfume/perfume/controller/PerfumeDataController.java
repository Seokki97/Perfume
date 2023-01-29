package com.example.perfume.perfume.controller;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.PerfumeDto;
import com.example.perfume.perfume.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perfume")
public class PerfumeDataController {

    private final PerfumeService perfumeService;

    @GetMapping("/save")
    public void saveData(Long id, PerfumeDto perfumeDto/*, Feature feature*/) throws IOException {
        perfumeService.savePerfumeData(id, perfumeDto /*,feature*/);

    }

    @PostMapping("/find_by_name")
    public ResponseEntity<Perfume> findByPerfumeName(@RequestBody String perfumeName) {
        return ResponseEntity.ok(perfumeService.findPerfumeByName(perfumeName));
    }

    @PostMapping("/find_by_brand")
    public ResponseEntity<Perfume> findByBrandName(@RequestBody String brandName) {
        return ResponseEntity.ok(perfumeService.findPerfumeByBrand(brandName));
    }

    @GetMapping("/delete")
    public void deleteAllData() {
        perfumeService.deleteAllData();
    }

    @GetMapping("/show_all_data")
    public ResponseEntity<List<Perfume>> showAllData() {
        return ResponseEntity.ok(perfumeService.showAllPerfumeData());
    }
}
