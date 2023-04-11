package com.example.perfume.perfume.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.service.PerfumeCSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.repository.PerfumeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PerfumeData {

    private final PerfumeRepository perfumeRepository;

    private final PerfumeCSVFileLoading perfumeCsvFileLoading;

    public PerfumeData(PerfumeRepository perfumeRepository, PerfumeCSVFileLoading perfumeCsvFileLoading) {
        this.perfumeRepository = perfumeRepository;
        this.perfumeCsvFileLoading = perfumeCsvFileLoading;
    }

    private PerfumeResponseDto makePerfumeList(Long id, int firstIndex, PerfumeList perfumeList) {
        return new PerfumeResponseDto(id,
                perfumeList.getPerfumeName().get(firstIndex),
                perfumeList.getPerfumeFeature().get(firstIndex),
                perfumeList.getPerfumeBrand().get(firstIndex),
                perfumeList.getPerfumeImageUrl().get(firstIndex));
    }

    public void savePerfumeData(Long id, PerfumeList perfume) throws IOException {
        perfume = perfumeCsvFileLoading.extractAllPerfumeData(perfume);
        int perfumeListSize = perfume.getMaxSize();
        for (int firstIndex = 0; firstIndex < perfumeListSize; firstIndex++) {
            Perfume perfumeDataSet = makePerfumeList(id, firstIndex, perfume).toEntity();
            perfumeRepository.save(perfumeDataSet);
        }
    }

    public void deleteAllData() {
        perfumeRepository.deleteAll();
    }

}
