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
    List<Survey> findBySecondAnswerOfSurveyLike(String secondAnswerOfSurvey);
    List<Survey> findByThirdAnswerOfSurvey(String thirdAnswerOfSurvey);
    List<Survey> findByFourthAnswerOfSurvey(String fourthAnswerOfSurvey);
    List<Survey> findByFifthAnswerOfSurvey(String fifthAnswerOfSurvey);
}
