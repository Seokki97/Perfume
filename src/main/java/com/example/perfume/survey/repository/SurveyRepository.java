package com.example.perfume.survey.repository;

import com.example.perfume.survey.domain.Survey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    List<Survey> findAll(@Nullable Specification<Survey> specification);

    List<Survey> findByFirstAnswerOfSurvey(String firstAnswerOfSurvey);
    List<Survey> findBySecondAnswerOfSurvey(String secondAnswerOfSurvey);
    List<Survey> findByThirdAnswerOfSurveyLike(String thirdAnswerOfSurvey);
    List<Survey> findByFourthAnswerOfSurveyLike(String fourthAnswerOfSurvey);
    List<Survey> findByFifthAnswerOfSurvey(String fifthAnswerOfSurvey);



}
