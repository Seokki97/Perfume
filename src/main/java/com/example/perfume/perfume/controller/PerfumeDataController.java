package com.example.perfume.perfume.controller;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.service.PerfumeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/perfume")
public class PerfumeDataController {

    private final PerfumeService perfumeService;

    @GetMapping("/save")
    public void saveData(Long id, PerfumeDto perfumeDto) throws IOException {
        perfumeService.savePerfumeData(id);

    }

    @PostMapping("/find_by_name")
    public ResponseEntity<Perfume> findByPerfumeName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
        return ResponseEntity.ok(perfumeService.findPerfumeByName(perfumeRequestDto));
    }

    @PostMapping("/find_by_brand")
    public ResponseEntity<List<Perfume>> findByBrandName(@RequestBody PerfumeRequestDto perfumeRequestDto) {
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

}
