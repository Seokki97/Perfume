package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.PerfumeResponseDto;
import com.example.perfume.perfume.exception.BrandNotFoundException;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PerfumeServiceTest {

    @Mock
    private PerfumeRepository perfumeRepository;

    @InjectMocks
    private PerfumeService perfumeService;

    @DisplayName("사용자가 원하는 향수를 이름으로 찾는다. 해당 향수가 없을 시 커스텀 Exception을 발생시킨다.")
    @Test
    void findPerfumeByName() {
        PerfumeRequestDto perfumeRequestDto = PerfumeRequestDto.builder()
                .perfumeName("에르")
                .build();

        Perfume perfume = Perfume.builder()
                .perfumeName("에르메스")
                .build();

        List<Perfume> mockPerfume = new ArrayList<>();

        mockPerfume.add(perfume);

        Mockito.when(perfumeRepository.findByPerfumeNameContaining("에르"))
                .thenReturn(mockPerfume);

        Mockito.when(perfumeRepository.findByPerfumeNameContaining("예외"))
                .thenThrow(PerfumeNotFoundException.class);
        List<Perfume> expected = perfumeService.findPerfumeListByName(perfumeRequestDto);

        PerfumeRequestDto exception = PerfumeRequestDto.builder()
                .perfumeName("예외")
                .build();

        assertAll(
                () -> assertEquals(expected.get(0).getPerfumeName(), "에르메스"),
                () -> assertThrows(PerfumeNotFoundException.class, () -> perfumeService.findPerfumeListByName(exception))
        );
    }

    @DisplayName("사용자가 원하는 향수를 브랜드로 찾는다. 해당 향수가 없을 시 커스텀 Exception을 발생시킨다.")
    @Test
    void findPerfumeByBrand() {
        PerfumeRequestDto perfumeRequestDto = PerfumeRequestDto.builder()
                .brandName("샤넬")
                .build();

        Perfume perfume = Perfume.builder()
                .brandName("샤넬")
                .build();

        List<Perfume> mockPerfume = new ArrayList<>();

        mockPerfume.add(perfume);

        Mockito.when(perfumeRepository.findByBrandNameContaining("샤넬"))
                .thenReturn(mockPerfume);

        Mockito.when(perfumeRepository.findByBrandNameContaining("예외"))
                .thenThrow(BrandNotFoundException.class);
        List<Perfume> actual = perfumeService.findPerfumeByBrand(perfumeRequestDto);

        PerfumeRequestDto exception = PerfumeRequestDto.builder()
                .brandName("예외")
                .build();

        assertAll(
                () -> assertEquals(actual.get(0).getBrandName(), "샤넬"),
                () -> assertThrows(BrandNotFoundException.class, () -> perfumeService.findPerfumeByBrand(exception))
        );
    }

    @DisplayName("향수 번호로 향수 정보를 조회한다. 해당 향수가 존재하지 않을 시 커스텀 Exception을 발생시킨다.")
    @Test
    void findById() {
        Perfume mockPerfume = Perfume.builder()
                .id(1l)
                .build();

        Mockito.when(perfumeRepository.findById(1l)).thenReturn(Optional.ofNullable(mockPerfume));
        Mockito.when(perfumeRepository.findById(10l)).thenThrow(PerfumeNotFoundException.class);
        Perfume expected = perfumeService.findPerfumeById(1l);

        assertAll(
                () -> assertEquals(expected.getId(), 1l),
                () -> assertThrows(PerfumeNotFoundException.class, () -> perfumeRepository.findById(10l))
        );
    }

    @DisplayName("전체 향수 리스트를 조회한다.")
    @Test
    void findAllData() {
        Perfume list1 = Perfume.builder()
                .id(1l)
                .build();

        Perfume list2 = Perfume.builder()
                .id(2l)
                .build();

        List<Perfume> mockPerfumes = new ArrayList<>();

        mockPerfumes.add(list1);
        mockPerfumes.add(list2);

        Mockito.when(perfumeRepository.findAll()).thenReturn(mockPerfumes);
        List<Perfume> actual = perfumeService.showAllPerfumeData();

        assertIterableEquals(mockPerfumes, actual);
    }


}
