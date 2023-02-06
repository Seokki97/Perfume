package com.example.perfume.perfume.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.service.PerfumeCSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.repository.PerfumeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;

    private final PerfumeCSVFileLoading perfumeCsvFileLoading;



    public PerfumeService(PerfumeRepository perfumeRepository, PerfumeCSVFileLoading perfumeCsvFileLoading) {
        this.perfumeRepository = perfumeRepository;
        this.perfumeCsvFileLoading = perfumeCsvFileLoading;
    }


    public void savePerfumeData(Long id,PerfumeList perfumeList) throws IOException {
        perfumeList = perfumeCsvFileLoading.extractAllPerfumeData(perfumeList);

        for (int i = 0; i < perfumeList.getMaxSize(); i++) {

            PerfumeResponseDto perfumeResponseDto = new PerfumeResponseDto(id,
                    perfumeList.getPerfumeName().get(i),
                    perfumeList.getPerfumeFeature().get(i),
                    perfumeList.getPerfumeBrand().get(i),
                    perfumeList.getPerfumeImageUrl().get(i));

            Perfume perfumeDataSet = perfumeResponseDto.toEntity();
            perfumeRepository.save(perfumeDataSet);
        }
    }

    public Perfume findPerfumeByName(PerfumeRequestDto perfumeRequestDto) {
        Perfume perfume = perfumeRepository.findByPerfumeNameLike(perfumeRequestDto.getPerfumeName())
                .orElseThrow(() -> new IllegalArgumentException("해당 향수를 찾을 수 없습니다"));
        return perfume;
    }

    public List<Perfume> findPerfumeByBrand(PerfumeRequestDto perfumeRequestDto) {
        List<Perfume> perfume = perfumeRepository.findByBrandNameLike(perfumeRequestDto.getBrandName())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드를 찾을 수 없습니다."));

        return perfume;
    }

    public void deleteAllData() {
        perfumeRepository.deleteAll();
    }

    public List<Perfume> showAllPerfumeData() {

        return perfumeRepository.findAll().stream().collect(Collectors.toList());
    }

    public boolean isExistPerfumeName(PerfumeRequestDto perfumeRequestDto) {
        return perfumeRepository.existsByPerfumeName(perfumeRequestDto.getPerfumeName());
    }

}
