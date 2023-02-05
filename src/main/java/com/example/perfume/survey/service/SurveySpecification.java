package com.example.perfume.survey.service;

import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.example.perfume.survey.domain.Feature;
import com.example.perfume.survey.dto.featureDto.FeatureRequestDto;
import com.example.perfume.survey.repository.FeatureRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class SurveySpecification {

    public static Specification<Feature> findByFirstFeature(String firstAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstAnswerOfSurvey"), firstAnswer);
    }

    public static Specification<Feature> findBySecondFeature(String secondAnswer) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("secondAnswerOfSurvey"), secondAnswer);
    }

    public static Specification<Feature> findByThirdFeature(String thirdAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("thirdAnswerOfSurvey"), thirdAnswer);
    }

    public static Specification<Feature> findByFourthFeature(String fourthAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fourthAnswerOfSurvey"), fourthAnswer);
    }

    public static Specification<Feature> findByFifthFeature(String fifthAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fifthAnswerOfSurvey"), fifthAnswer);
    }

    public static Specification<Feature> findFeature(String answer){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(answer),answer);
    }
}
