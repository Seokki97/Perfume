package com.example.perfume.perfume.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.service.PerfumeCSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.exception.BrandNotFoundException;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
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

    public List<Perfume> findPerfumeByName(PerfumeRequestDto perfumeRequestDto) {

        List<Perfume> perfume = perfumeRepository.findByPerfumeNameContaining(perfumeRequestDto.getPerfumeName());
        isPerfumeListEmpty(perfume);
        return perfume;
    }

    public List<Perfume> findPerfumeByBrand(PerfumeRequestDto perfumeRequestDto) {
        List<Perfume> perfume = perfumeRepository.findByBrandNameContaining(perfumeRequestDto.getBrandName());
        isBrandListEmpty(perfume);
        return perfume;
    }

    public Perfume findPerfumeById(Long id) {
        return perfumeRepository.findById(id).orElseThrow(PerfumeNotFoundException::new);
    }

    public void deleteAllData() {
        perfumeRepository.deleteAll();
    }

    public List<Perfume> showAllPerfumeData() {
        List<Perfume> perfumeList = perfumeRepository.findAll().stream().collect(Collectors.toList());

        isPerfumeListEmpty(perfumeList);
        return perfumeList;
    }

    private boolean isPerfumeListEmpty(List<Perfume> perfumeList) {
        if (perfumeList.isEmpty()) {
            throw new PerfumeNotFoundException();
        }
        return true;
    }

    private boolean isBrandListEmpty(List<Perfume> brandList) {
        if (brandList.isEmpty()) {
            throw new BrandNotFoundException();
        }
        return true;
    }

    public List<String> showBrandList() {
        return perfumeRepository.findAll().stream().map(data -> data.getBrandName())
                .distinct().collect(Collectors.toList());
    }

    public PerfumeResponseDto showPerfumeImage(String perfumeName) {
        Perfume perfume = perfumeRepository.findByPerfumeName(perfumeName)
                .orElseThrow(PerfumeNotFoundException::new);

        return PerfumeResponseDto.builder()
                .id(perfume.getId())
                .perfumeFeature(perfume.getPerfumeFeature())
                .perfumeName(perfume.getPerfumeName())
                .brandName(perfume.getBrandName())
                .perfumeImageUrl(perfume.getPerfumeImageUrl())
                .build();
    }
}
