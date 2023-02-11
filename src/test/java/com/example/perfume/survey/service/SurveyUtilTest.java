package com.example.perfume.survey.service;

import com.example.perfume.crawling.domain.survey.SurveyList;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class SurveyUtilTest {

    @Autowired
    private SurveyUtil surveyUtil;
    @Autowired
    private DataService dataService;

    @Autowired
    private SurveyRepository surveyRepository;

    @DisplayName("두개의 리스트를 합친다.")
    @Test
    void addList() {
        List<Survey> survey1 = surveyRepository.findByStyleAnswer("디폴트");
        List<Survey> survey2 = surveyRepository.findByStyleAnswer("캐쥬얼");
        int firstSize = surveyRepository.findByStyleAnswer("디폴트").size();
        int secondSize = surveyRepository.findByStyleAnswer("캐쥬얼").size();
        int resultSize = firstSize + secondSize;
        List<Survey> addedList = surveyUtil.addList(survey1, survey2);

        assertAll(
                () -> assertEquals(resultSize,addedList.size())
        );
    }

    @DisplayName("두개의 리스트에서 같은 항목들을 찾는다.")
    @Test
    void filterList(){
        List<Survey> survey1 = surveyRepository.findByStyleAnswer("디폴트");
        List<Survey> survey2 = surveyRepository.findByStyleAnswer("캐쥬얼");
        int filteredList = surveyUtil.compareTwoFilteredSurveyData(survey1,survey2).size();
        int result = 0;

        assertAll(
                () -> assertEquals(result,filteredList)
        );
    }
}
