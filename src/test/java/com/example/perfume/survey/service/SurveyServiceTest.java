package com.example.perfume.survey.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.repository.SurveyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyUtil surveyUtil;

    @DisplayName("선택된 응답값을 기준으로 유사한 향수를 추천한다.")
    @Test
    void showSimilarPerfume() {

        Perfume perfume = Perfume.builder()
                .id(4l)
                .build();

        Perfume perfume1 = Perfume.builder()
                .id(3l)
                .build();

        Survey vanillaPerfume = Survey.builder()
                .perfume(perfume)
                .genderAnswer("여자")
                .moodAnswer("시트러스")
                .scentAnswer("상큼한")
                .build();

        Survey citrusPerfume = Survey.builder()
                .perfume(perfume1)
                .genderAnswer("여자")
                .scentAnswer("시트러스")
                .moodAnswer("상큼한")
                .build();

        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(citrusPerfume);
        surveyList.add(vanillaPerfume);

        List<Perfume> perfumeList = new ArrayList<>();
        perfumeList.add(perfume);
        perfumeList.add(perfume1);

        when(surveyUtil.convertToPerfumeData(anyList())).thenReturn(perfumeList);
        when(surveyRepository.findSurveysByGenderScentAndMood
                (any(), any(), any())).thenReturn(surveyList);

        List<Perfume> actual = surveyService.showSimilarPerfumeList(citrusPerfume);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2,actual.size())
        );
    }
}
