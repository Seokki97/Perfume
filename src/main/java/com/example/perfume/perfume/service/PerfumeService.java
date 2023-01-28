package com.example.perfume.perfume.service;


import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.PerfumeDto;
import com.example.perfume.perfume.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;

    private final CSVFileLoading csvFileLoading;

    public void savePerfumeData(Long id, PerfumeDto perfumeDto/*, Feature feature*/) throws IOException {
        csvFileLoading.extractAllPerfumeData();
        for (int i = 0; i < csvFileLoading.setMaxListSize(); i++) {
            perfumeDto = new PerfumeDto(id,
                    csvFileLoading.getPerfumeName().get(i),
                    csvFileLoading.getPerfumeFeature().get(i),
                    csvFileLoading.getPerfumeBrand().get(i)/*,
                    feature*/);
            Perfume perfumeDataSet = perfumeDto.toEntity(/*feature*/);
            perfumeRepository.save(perfumeDataSet);
        }
    }
}
