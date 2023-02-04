package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Feature;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class SurveySpecification {


    public static Specification<Feature> findByFirstFeature(String answerOfSurvey) {
        System.out.println("answerOfSurvey" + answerOfSurvey);
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
    }

    public static Specification<Feature> findBySecondFeature(String answerOfSurvey) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
    }

    public static Specification<Feature> findByThirdFeature(String answerOfSurvey) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
    }

    public static Specification<Feature> findByFourthFeature(String answerOfSurvey) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
    }

    public static Specification<Feature> findFifthFeature(String answerOfSurvey) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
    }
}
