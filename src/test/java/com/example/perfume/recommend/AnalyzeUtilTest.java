package com.example.perfume.recommend;

import com.example.perfume.recommend.dto.analyze.AnalyzeResponse;
import com.example.perfume.recommend.service.analyze.AnalyzeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AnalyzeUtilTest {

    @Autowired
    private AnalyzeUtil analyzeUtil;

    @DisplayName("리스트의 요소 갯수를 센다.")
    @Test
    void countElement() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add("2");
        }
        int actual = analyzeUtil.countElement(list).get("2").intValue();

        Assertions.assertEquals(20, actual);
    }

    @DisplayName("분석된 향수리스트의 갯수를 센다")
    @Test
    void analyzePerfumes() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 23; i++) {
            list.add("3");
            list.add("%");
        }
        list.add("3");

        AnalyzeResponse analyzeResponse = analyzeUtil.countPerfumeList(list);
        Long count = analyzeResponse.getCount();

        Assertions.assertAll(
                () -> Assertions.assertEquals(24L, count),
                () -> Assertions.assertEquals("3", analyzeResponse.getElementName())
        );
    }
}
