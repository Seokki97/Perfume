package com.example.perfume.perfume.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.service.PerfumeCSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.PerfumeResponseDto;
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
        return PerfumeResponseDto.builder()
                .id(id)
                .perfumeName(perfumeList.getPerfumeName().get(firstIndex))
                .perfumeFeature(perfumeList.getPerfumeFeature().get(firstIndex))
                .brandName(perfumeList.getPerfumeBrand().get(firstIndex))
                .perfumeImageUrl(perfumeList.getPerfumeImageUrl().get(firstIndex))
                .maintenance(perfumeList.getMaintenance().get(firstIndex))
                .build();
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
