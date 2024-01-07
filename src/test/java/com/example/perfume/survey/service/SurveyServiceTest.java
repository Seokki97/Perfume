package com.example.perfume.survey.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.survey.domain.Question;
import com.example.perfume.survey.domain.Survey;
import com.example.perfume.survey.repository.SurveyRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    private SimilarPerfumeService similarPerfumeService;

    @Mock
    private SurveyUtil surveyUtil;

    @DisplayName("선택된 응답값을 기준으로 유사한 향수를 추천한다.")
    @Test
    void showSimilarPerfume() {

        Perfume perfume = Perfume.builder()
                .perfumeId(4l)
                .build();

        Perfume perfume1 = Perfume.builder()
                .perfumeId(3l)
                .build();

        Survey vanillaPerfume = Survey.builder()
                .perfume(perfume)
                .question(new Question("여자", "시트러스", "상큼한", null, null))
                .build();

        Survey citrusPerfume = Survey.builder()
                .perfume(perfume1)
                .question(new Question("여자", "시트러스", "상큼한", null, null))
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

        List<Perfume> actual = similarPerfumeService.showSimilarPerfumeList(citrusPerfume);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, actual.size())
        );
    }
}
