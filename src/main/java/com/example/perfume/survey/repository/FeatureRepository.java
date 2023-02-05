package com.example.perfume.survey.repository;

import com.example.perfume.survey.domain.Feature;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>, JpaSpecificationExecutor<Feature> {

    List<Feature> findAll(@Nullable Specification<Feature> specification);

    List<Feature> findByFirstAnswerOfSurvey(String firstAnswerOfSurvey);
    List<Feature> findBySecondAnswerOfSurveyLike(String secondAnswerOfSurvey);
    List<Feature> findByThirdAnswerOfSurvey(String thirdAnswerOfSurvey);
    List<Feature> findByFourthAnswerOfSurvey(String fourthAnswerOfSurvey);
    List<Feature> findByFifthAnswerOfSurvey(String fifthAnswerOfSurvey);
}
