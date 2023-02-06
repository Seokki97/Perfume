package com.example.perfume.survey.service;

import com.example.perfume.survey.domain.Survey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SurveySpecification {

    public static Specification<Survey> findByFirstFeature(String firstAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstAnswerOfSurvey"), firstAnswer);
    }

    public static Specification<Survey> findBySecondFeature(String secondAnswer) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("secondAnswerOfSurvey"), secondAnswer);
    }

    public static Specification<Survey> findByThirdFeature(String thirdAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("thirdAnswerOfSurvey"), thirdAnswer);
    }

    public static Specification<Survey> findByFourthFeature(String fourthAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fourthAnswerOfSurvey"), fourthAnswer);
    }

    public static Specification<Survey> findByFifthFeature(String fifthAnswer) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fifthAnswerOfSurvey"), fifthAnswer);
    }

    public static Specification<Survey> findFeature(String answer){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(answer),answer);
    }
}
