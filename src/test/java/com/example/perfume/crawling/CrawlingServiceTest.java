package com.example.perfume.crawling;

import com.example.perfume.crawling.service.CSVFileLoading;
import com.example.perfume.crawling.service.PerfumeCSVFileLoading;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CrawlingServiceTest {


    @DisplayName("배열을 순회하여 리스트를 만든다")
    @Test
    void makeList(){
        CSVFileLoading csvFileLoading = new CSVFileLoading();
        String[] test = {"ㅋ","ㄴ","ㅋ","ㄴ"};
        List<String> testList = new ArrayList<>();
        List<String> actual = csvFileLoading.makeList(test,testList);
        List<String> expected = new ArrayList<>();
        expected.add(test[0]);
        expected.add(test[1]);
        expected.add(test[2]);
        expected.add(test[3]);

        assertAll(
                () ->  assertEquals(expected,actual)
        );
    }
}
