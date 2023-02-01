package com.example.perfume.perfume.service;

import com.example.perfume.crawling.service.CSVFileLoading;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.repository.PerfumeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;

    private final CSVFileLoading csvFileLoading;

    public PerfumeService(PerfumeRepository perfumeRepository, CSVFileLoading csvFileLoading){
        this.perfumeRepository = perfumeRepository;
        this.csvFileLoading = csvFileLoading;
    }
    public void savePerfumeData(Long id) throws IOException {
        csvFileLoading.extractAllPerfumeData();
        for (int i = 0; i < csvFileLoading.setMaxListSize(); i++) {

            PerfumeDto perfumeDto = new PerfumeDto(id,
                    csvFileLoading.getPerfumeName().get(i),
                    csvFileLoading.getPerfumeFeature().get(i),
                    csvFileLoading.getPerfumeBrand().get(i),
                    csvFileLoading.getPerfumeImageUrl().get(i));

            Perfume perfumeDataSet = perfumeDto.toEntity();
            perfumeRepository.save(perfumeDataSet);
        }
    }

    public Perfume findPerfumeByName(PerfumeRequestDto perfumeRequestDto) {
        Perfume perfume = perfumeRepository.findByPerfumeName(perfumeRequestDto.getPerfumeName())
                .orElseThrow(() -> new IllegalArgumentException("해당 향수를 찾을 수 없습니다"));
        return perfume;
    }

    public List<Perfume> findPerfumeByBrand(PerfumeRequestDto perfumeRequestDto) {
        List<Perfume> perfume = perfumeRepository.findByBrandName(perfumeRequestDto.getBrandName())
                .orElseThrow(() -> new IllegalArgumentException("해당 향수를 찾을 수 없습니다."));

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
