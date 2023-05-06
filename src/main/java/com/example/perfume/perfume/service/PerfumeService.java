package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.dto.story.StoryResponse;
import com.example.perfume.perfume.exception.BrandNotFoundException;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;

    public PerfumeService(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    public List<Perfume> findPerfumeByName(PerfumeRequestDto perfumeRequestDto) {
        List<Perfume> perfume = perfumeRepository.findByPerfumeNameContaining(perfumeRequestDto.getPerfumeName());
        validatePerfumeListEmpty(perfume);
        return perfume;
    }

    public List<Perfume> findPerfumeByBrand(PerfumeRequestDto perfumeRequestDto) {
        List<Perfume> perfume = perfumeRepository.findByBrandNameContaining(perfumeRequestDto.getBrandName());
        validateBrandListEmpty(perfume);
        return perfume;
    }

    public Perfume findPerfumeById(Long id) {
        return perfumeRepository.findById(id).orElseThrow(PerfumeNotFoundException::new);
    }

    public List<Perfume> showAllPerfumeData() {
        List<Perfume> perfumeList = perfumeRepository.findAll();

        validatePerfumeListEmpty(perfumeList);
        return perfumeList;
    }

    private void validatePerfumeListEmpty(List<Perfume> perfumeList) {
        if (perfumeList.isEmpty()) {
            throw new PerfumeNotFoundException();
        }
    }

    private void validateBrandListEmpty(List<Perfume> brandList) {
        if (brandList.isEmpty()) {
            throw new BrandNotFoundException();
        }
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
