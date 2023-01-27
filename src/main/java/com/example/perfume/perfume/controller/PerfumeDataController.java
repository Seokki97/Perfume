package com.example.perfume.perfume.controller;

import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.dto.PerfumeDto;
import com.example.perfume.perfume.service.CSVFileLoading;
import com.example.perfume.perfume.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/perfume")
public class PerfumeDataController {

    private final PerfumeService perfumeService;
    @GetMapping("/save")
    public String saveData(PerfumeDto perfumeDto, Feature feature) throws IOException {
        perfumeService.savePerfumeList(perfumeDto,feature);
    return "success";
    }

}
