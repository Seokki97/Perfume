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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
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
        List<Perfume> actual = perfumeService.findPerfumeListByName(perfumeRequestDto);

        PerfumeRequestDto exception = PerfumeRequestDto.builder()
                .perfumeName("예외")
                .build();

        assertAll(
                () -> assertEquals(actual.get(0).getPerfumeName(), "에르메스"),
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
        Perfume perfume = Perfume.builder()
                .id(1l)
                .perfumeName("조말론")
                .perfumeFeature("특징")
                .perfumeImageUrl("예시")
                .brandName("조말론")
                .build();
        perfumeRepository.save(perfume);
        Perfume expected = perfumeService.findPerfumeById(perfume.getId());

        assertAll(
                () -> assertThat(perfume).usingRecursiveComparison().isEqualTo(expected),
                () -> assertThatThrownBy(() -> perfumeService.findPerfumeById(50l))
                        .isInstanceOf(PerfumeNotFoundException.class).hasMessage("해당 향수를 찾을 수 없습니다.")
        );
    }

    @DisplayName("전체 향수 리스트를 조회한다.")
    @Test
    void findAllData() {
        Perfume list1 = Perfume.builder()
                .id(1l)
                .perfumeName("조말론")
                .perfumeFeature("특징")
                .perfumeImageUrl("예시")
                .brandName("조말론")
                .build();
        Perfume list2 = Perfume.builder()
                .id(2l)
                .perfumeName("예씨")
                .perfumeFeature("특")
                .perfumeImageUrl("이미")
                .brandName("브랜드")
                .build();

        perfumeRepository.save(list1);
        perfumeRepository.save(list2);
        List<Perfume> actual = perfumeService.showAllPerfumeData();
        List<Perfume> expected = new ArrayList<>();
        expected.add(list1);
        expected.add(list2);

        assertThat(actual).usingRecursiveComparison()
                .isEqualTo(expected);

    }

    @DisplayName("향수 데이터가 없을 경우에 커스텀 Exception을 발생시킨다.")
    @Test
    void cannotFind() {
        assertThatThrownBy(() -> perfumeService.showAllPerfumeData())
                .isInstanceOf(PerfumeNotFoundException.class).hasMessage("해당 향수를 찾을 수 없습니다.");
    }

    @DisplayName("사용자가 쓰는 향수와 유사한 향수를 추천해준다")
    @Test
    void selectSimilarPerfume() {
        PerfumeResponseDto perfumeResponseDto = PerfumeResponseDto.builder()
                .id(4l)
                .perfumeName("구찌블룸네타레")
                .perfumeFeature("구찌 블룸 정원 신비로운 노란 달콤함을 불러일으키는 넥타린과 허니서클 향기")
                .brandName("구찌블룸네타레")
                .perfumeImageUrl("//perfumegraphy.com/web/product/medium/202212/dd852ce8c0e3d3edfa62d472f80a70e1.jpg")
                .build();
        Perfume perfume = perfumeResponseDto.toEntity();
        perfumeRepository.save(perfume);
        Perfume perfume1 = Perfume.builder().id(54l)
                .perfumeFeature("Das")
                .perfumeImageUrl("asd")
                .perfumeName("asd")
                .brandName("asd")
                .build();
        perfumeRepository.save(perfume1);
        PerfumeResponseDto expected = PerfumeResponseDto.builder()
                .id(54l).build();


    }

}
