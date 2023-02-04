package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Feature;
import com.example.perfume.perfume.dto.featureDto.FeatureDto;
import com.example.perfume.perfume.dto.featureDto.FeatureRequestDto;
import com.example.perfume.perfume.dto.featureDto.FeatureResponseDto;
import com.example.perfume.perfume.repository.FeatureRepository;
import com.example.perfume.perfume.repository.PerfumeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyService {

    private final FeatureRepository featureRepository;

    public SurveyService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    //첫번째 질문, 두번째 질문
    public List<Feature> findDataFromAnswerData(List<String> answerList) {
        Specification<Feature> specification = ((root, query, criteriaBuilder) -> null);
        specification = specification.and(SurveySpecification.findFifthFeature(answerList.get(0)));
        specification = specification.and(SurveySpecification.findSecondFeature(answerList.get(1)));
        specification = specification.and(SurveySpecification.findThirdFeature(answerList.get(2)));
        specification = specification.and(SurveySpecification.findFourthFeature(answerList.get(3)));
        specification = specification.and(SurveySpecification.findFifthFeature(answerList.get(4)));
        return featureRepository.findAll(specification);
    }

    public void saveAllData() {
        featureRepository.save(new Feature(1l,"사랑 거짓 참된 자유"));
    }


}
