package com.example.perfume.survey.repository;

import com.example.perfume.survey.domain.Survey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {


    Optional<Survey> findById(Long id);
    List<Survey> findByFirstAnswerOfSurvey(String firstAnswerOfSurvey);

    List<Survey> findBySecondAnswerOfSurvey(String secondAnswerOfSurvey);


    List<Survey> findByThirdAnswerOfSurveyContaining(String thirdAnswerOfSurvey);

    List<Survey> findByThirdAnswerOfSurveyNotContaining(String thirdAnswerOfSurvey);



    List<Survey> findByFourthAnswerOfSurveyContaining(String fourthAnswerOfSurvey);

    List<Survey> findByFourthAnswerOfSurvey(String fourthAnswerOfSurvey);

    List<Survey> findByFifthAnswerOfSurvey(String fifthAnswerOfSurvey);


}
