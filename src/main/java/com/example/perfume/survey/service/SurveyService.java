package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Feature;
import com.example.perfume.survey.dto.featureDto.FeatureRequestDto;
import com.example.perfume.survey.repository.FeatureRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SurveyService {

    private final FeatureRepository featureRepository;

    private final SurveyUtil surveyUtil;

    public SurveyService(FeatureRepository featureRepository, SurveyUtil surveyUtil) {
        this.featureRepository = featureRepository;
        this.surveyUtil = surveyUtil;
    }

    //첫번째 질문, 두번째 질문
    public List<Feature> findDataFromAnswerData(FeatureRequestDto answerOfSurvey) {
        List<String> answerList = surveyUtil.splitAnswerOfSurvey(answerOfSurvey.getAnswerOfSurvey());
        //여기까지 됨

        Specification<Feature> specification;
        specification = SurveySpecification.findByFirstFeature("사랑 자유 거짓 참된");
        List<Feature> feature = featureRepository.findAll(specification);
        System.out.println("스페시피" + specification.toString());
        System.out.println("엔서리스트" + answerList.toString());
        System.out.println("피쳐" + feature.toString());
        return feature;
    }

    public void saveAllData(Long id) {
        Feature feature = new Feature(id, "사랑 자유 거짓 참된");
        featureRepository.save(feature);
    }


}
