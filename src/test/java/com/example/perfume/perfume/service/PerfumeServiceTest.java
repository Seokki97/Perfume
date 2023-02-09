package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeResponseDto;
import com.example.perfume.perfume.repository.PerfumeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PerfumeServiceTest {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private PerfumeService perfumeService;


    @DisplayName("사용자가 원하는 향수를 이름으로 찾는다.")
    @Test
    void findPerfumeByName() {
        Perfume actual = perfumeRepository.findByPerfumeNameContaining("오도랑쥬").get();

        Perfume expected = Perfume.builder()
                .perfumeName("에르메스 오도랑쥬")
                .perfumeFeature("나른함 가득한 늦은 오후 커튼 사이로 들어오는 햇살을 타고 흐르는 오렌지와 레몬의 상큼함")
                .brandName("에르메스")
                .perfumeImageUrl("//perfumegraphy.com/web/product/medium/202207/8f734a2482a65006e53da3a75a64e0a0.jpg")
                .build();

        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);

    }

    @DisplayName("사용자가 원하는 향수를 브랜드로 찾는다")
    @Test
    void findPerfumeByBrand() {
        Perfume actual = perfumeRepository.findByBrandNameContaining("구찌블룸네타레").get();

        Perfume expected = Perfume.builder()
                .perfumeName("구찌블룸네타레")
                .perfumeFeature("구찌 블룸 정원 신비로운 노란 달콤함을 불러일으키는 넥타린과 허니서클 향기")
                .brandName("구찌블룸네타레")
                .perfumeImageUrl("//perfumegraphy.com/web/product/medium/202212/dd852ce8c0e3d3edfa62d472f80a70e1.jpg")                .build();

        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }
}
