package com.example.perfume.perfume.service;

import com.example.perfume.perfume.domain.Feature;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

@Service
public class SurveySpecification {


    public static Specification<Feature> findFirstFeature(String answerOfSurvey) {
        return new Specification<Feature>() {
            @Override
            public Predicate toPredicate(Root<Feature> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
            }
        };
    }

    public static Specification<Feature> findSecondFeature(String answerOfSurvey) {
        return new Specification<Feature>() {
            @Override
            public Predicate toPredicate(Root<Feature> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
            }
        };
    }

    public static Specification<Feature> findThirdFeature(String answerOfSurvey) {
        return new Specification<Feature>() {
            @Override
            public Predicate toPredicate(Root<Feature> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
            }
        };
    }

    public static Specification<Feature> findFourthFeature(String answerOfSurvey) {
        return new Specification<Feature>() {
            @Override
            public Predicate toPredicate(Root<Feature> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
            }
        };
    }

    public static Specification<Feature> findFifthFeature(String answerOfSurvey) {
        return new Specification<Feature>() {
            @Override
            public Predicate toPredicate(Root<Feature> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("answerOfSurvey"), answerOfSurvey);
            }
        };
    }
}
