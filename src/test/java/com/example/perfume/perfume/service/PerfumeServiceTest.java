package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.PerfumeResponseDto;
import com.example.perfume.perfume.exception.BrandNotFoundException;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.perfume.repository.PerfumeRepository;
import com.example.perfume.survey.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PerfumeServiceTest {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private PerfumeService perfumeService;
    @Autowired
    private SurveyRepository surveyRepository;

    @DisplayName("사용자가 원하는 향수를 이름으로 찾는다. 해당 향수가 없을 시 커스텀 Exception을 발생시킨다.")
    @Test
    void findPerfumeByName() {
        Perfume actual = Perfume.builder()
                .perfumeName("에르메스 오도랑쥬")
                .perfumeFeature("나른함 가득한 늦은 오후 커튼 사이로 들어오는 햇살을 타고 흐르는 오렌지와 레몬의 상큼함")
                .brandName("에르메스")
                .perfumeImageUrl("//perfumegraphy.com/web/product/medium/202207/8f734a2482a65006e53da3a75a64e0a0.jpg")
                .build();
        perfumeRepository.save(actual);

        PerfumeRequestDto perfumeRequestDto = PerfumeRequestDto.builder()
                .perfumeName("에르메스 오도랑쥬")
                .perfumeFeature("나른함 가득한 늦은 오후 커튼 사이로 들어오는 햇살을 타고 흐르는 오렌지와 레몬의 상큼함")
                .brandName("에르메스")
                .perfumeImageUrl("//perfumegraphy.com/web/product/medium/202207/8f734a2482a65006e53da3a75a64e0a0.jpg")
                .build();
        List<Perfume> expectedList = perfumeService.findPerfumeByName(perfumeRequestDto);
        Perfume expected = expectedList.get(0);
        PerfumeRequestDto exception = PerfumeRequestDto.builder()
                .perfumeName("예외발생")
                .build();
        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected),
                () -> assertThatThrownBy(() -> perfumeService.findPerfumeByName(exception))
                        .isInstanceOf(PerfumeNotFoundException.class).hasMessage("해당 향수를 찾을 수 없습니다.")
        );
    }

    @DisplayName("사용자가 원하는 향수를 브랜드로 찾는다. 해당 향수가 없을 시 커스텀 Exception을 발생시킨다.")
    @Test
    void findPerfumeByBrand() {
        //Db에서찾기

        Perfume expected = Perfume.builder()
                .perfumeName("구찌블룸네타레")
                .perfumeFeature("구찌 블룸 정원 신비로운 노란 달콤함을 불러일으키는 넥타린과 허니서클 향기")
                .brandName("구찌블룸네타레")
                .perfumeImageUrl("//perfumegraphy.com/web/product/medium/202212/dd852ce8c0e3d3edfa62d472f80a70e1.jpg")
                .build();
        perfumeRepository.save(expected);
        List<Perfume> actualList = perfumeRepository.findByBrandNameContaining("구찌블룸네타레");
        Perfume actual = actualList.get(0);

        PerfumeRequestDto perfumeRequestDto = PerfumeRequestDto.builder()
                .perfumeName("구찌블룸네타레")
                .perfumeFeature("구찌 블룸 정원 신비로운 노란 달콤함을 불러일으키는 넥타린과 허니서클 향기")
                .brandName("구찌블룸네타레")
                .perfumeImageUrl("//perfumegraphy.com/web/product/medium/202212/dd852ce8c0e3d3edfa62d472f80a70e1.jpg")
                .build();
        List<Perfume> actualList1 = perfumeService.findPerfumeByBrand(perfumeRequestDto);
        Perfume actual1 = actualList1.get(0);

        PerfumeRequestDto exception = PerfumeRequestDto.builder()
                .brandName("예외발생").
                build();
        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected),
                () -> assertThat(actual1).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected),
                () -> assertThatThrownBy(() -> perfumeService.findPerfumeByBrand(exception))
                        .isInstanceOf(BrandNotFoundException.class).hasMessage("해당 브랜드의 향수 데이터가 없습니다.")
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
