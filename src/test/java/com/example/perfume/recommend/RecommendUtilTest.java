package com.example.perfume.recommend;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.recommend.exception.RecommendNotFoundException;
import com.example.perfume.recommend.service.RecommendUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecommendUtilTest {

    @DisplayName("추천 결과중에 랜덤으로 향수를 선택하여 반환한다.")
    @Test
    void createRandomPerfume() {
        Perfume perfume = Perfume
                .builder()
                .id(1l)
                .build();
        Perfume perfume2 = Perfume.builder()
                .id(2l)
                .build();

        List<Perfume> perfumeList = new ArrayList<>();
        List<Perfume> emptyList = new ArrayList<>();

        perfumeList.add(perfume);
        perfumeList.add(perfume2);

        int actual1 = 0;
        int actual2 = 1;

        Assertions.assertAll(
                () -> Assertions.assertThrows(RecommendNotFoundException.class, () -> RecommendUtils.createRandomPerfumeFromList(emptyList)),
                () -> assertThat(RecommendUtils.createRandomPerfumeFromList(perfumeList)).satisfiesAnyOf(
                result -> assertThat(actual1).isEqualTo(result),
                result -> assertThat(actual2).isEqualTo(result)
        ));

    }
}
